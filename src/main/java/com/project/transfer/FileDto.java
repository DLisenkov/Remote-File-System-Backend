package com.project.transfer;

import com.project.models.File;
import lombok.Builder;
import lombok.Data;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * File data transfer object
 */
@Data
@Builder
public class FileDto {

    /**
     * File content
     */
    private String content;

    /**
     * Method for getting file DTO by file
     * @param file file
     * @return file DTO like {@link FileDto}
     * @throws IOException if the contents of the file could not be read
     */
    public static FileDto from (File file) throws IOException {

        FileReader fileReader = new FileReader(file.getPath());
        Scanner scan = new Scanner(fileReader);
        StringBuilder content = new StringBuilder();
        while (scan.hasNextLine()) {
            content.append(scan.nextLine());
        }
        fileReader.close();

        return FileDto.builder().content(content.toString()).build();
    }
}
