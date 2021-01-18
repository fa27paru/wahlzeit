package org.wahlzeit.utils;

import java.lang.annotation.Documented;

@Documented
public @interface PatternInstance {
    String patternName();
    String[] participants();
}
