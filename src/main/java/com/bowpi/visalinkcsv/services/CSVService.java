package com.bowpi.visalinkcsv.services;

import com.bowpi.visalinkcsv.dto.LinkDTO;
import com.bowpi.visalinkcsv.dto.LinkRedes;
import com.bowpi.visalinkcsv.dto.ResponseDTO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CSVService {
    private final String TYPE = "text/csv";
    private final String[] HEADERS = {"Id", "Nombre", "Descripcion", "Precio"};

    public ResponseDTO process(MultipartFile file) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            responseDTO.setData(csvToLinks(file.getInputStream()));
            responseDTO.setMessage("CSV generated");
            responseDTO.setSuccess(true);
            return responseDTO;
        } catch (IOException e) {
            responseDTO.setMessage("Error Loading file");
            responseDTO.setSuccess(false);
            return responseDTO;
        }
    }

    public boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    private List<LinkDTO> csvToLinks(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withDelimiter(';').withIgnoreHeaderCase().withTrim())) {

            List<LinkDTO> linkDTOList = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                // Llamada al API de Visalink por acá
                LinkDTO linkDTO = LinkDTO.builder()
                        .codigo(csvRecord.get("Id"))
                        .nombre(csvRecord.get("Nombre"))
                        .descripcion(csvRecord.get("Descripcion"))
                        .titulo(csvRecord.get("Nombre"))
                        .precio(new BigDecimal(csvRecord.get("Precio")))
                        .estado("activo")
                        .redes(new ArrayList<>(Arrays.asList(
                                LinkRedes.builder().nombre("Email").url("https://linkdemo.com/email/" + csvRecord.get("Id")).build(),
                                LinkRedes.builder().nombre("Whatsapp").url("https://linkdemo.com/ws/" + csvRecord.get("Id")).build(),
                                LinkRedes.builder().nombre("Facebook").url("https://linkdemo.com/facebook/" + csvRecord.get("Id")).build()
                        )))
                        .visitas(0)
                        .ventas(0)
                        .build();
                linkDTOList.add(linkDTO);
            }

            return linkDTOList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
