package com.dust.launcher;

import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class Settings {

    @Builder.Default
    private final int width = 800;

    @Builder.Default
    private final int height = 400;

    @Builder.Default
    private final int fps = 60;

    @Builder.Default
    private final String title = "no_title";



}
