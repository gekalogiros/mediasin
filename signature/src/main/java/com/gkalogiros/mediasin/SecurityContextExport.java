package com.gkalogiros.mediasin;

import java.util.List;

public class SecurityContextExport {

    private List<SecurityContext> segments;

    public SecurityContextExport(final List<SecurityContext> segments) {
        this.segments = segments;
    }

    public void export() throws Exception {
        for (final SecurityContext securityContext : segments) {
            securityContext.save();
        }
    }
}
