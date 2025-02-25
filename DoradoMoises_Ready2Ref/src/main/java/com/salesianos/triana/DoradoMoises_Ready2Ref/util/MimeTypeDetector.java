package com.salesianos.triana.DoradoMoises_Ready2Ref.util;

import org.springframework.core.io.Resource;

public interface MimeTypeDetector {

    String getMimeType(Resource resource);

}
