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
public class DirectoryDto {
    private String name;

    public static DirectoryDto from (File file) {
        return DirectoryDto.builder()
                .name(file.getName())
                .build();
    }

    public static List<DirectoryDto> from (List<File> files) {
        return files.stream().map(DirectoryDto::from).collect(Collectors.toList());
    }
}
