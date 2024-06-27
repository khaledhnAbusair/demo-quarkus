package com.test;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DownloadFileResponse {
    private final byte[] image;
}
