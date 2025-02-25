package com.salesianos.triana.DoradoMoises_Ready2Ref.service.files;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.AbstractFileMetadata;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.FileMetadata;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class LocalFileMetadataImpl extends AbstractFileMetadata {

    public static FileMetadata of(String filename) {
        return LocalFileMetadataImpl.builder()
                .id(filename)
                .filename(filename)
                .build();
    }

}
