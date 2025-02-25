package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.file;

import lombok.Builder;

@Builder
public record FileResponse(
        String id,
        String name,
        String uri,
        String type,
        long size
)
{}
