package com.project.transfer;

import com.project.models.File;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDto {
    private String name;

    public static FileDto from (File file) {
        return FileDto.builder()
                .name(file.getName())
                .build();
    }

    public static List<FileDto> from (List<File> files) {
        return files.stream().map(FileDto::from).collect(Collectors.toList());
    }
}
