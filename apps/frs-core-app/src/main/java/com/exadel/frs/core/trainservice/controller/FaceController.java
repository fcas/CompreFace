package com.exadel.frs.core.trainservice.controller;

import static com.exadel.frs.core.trainservice.system.global.Constants.X_FRS_API_KEY_HEADER;
import com.exadel.frs.core.trainservice.service.FaceService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/faces")
@RestController
@RequiredArgsConstructor
public class FaceController {

    private final FaceService faceService;

    @GetMapping
    public Map<String, List<String>> findAllFaceNames(
            @RequestHeader(name = X_FRS_API_KEY_HEADER)
            final String appKey
    ) {
        return faceService.findAllFaceNames(appKey);
    }

    @DeleteMapping("/{faceName}")
    public void deleteFaceByName(
            @PathVariable
            final String faceName,
            @RequestHeader(name = X_FRS_API_KEY_HEADER)
            final String apiKey,
            @RequestParam(required = false, defaultValue = "force")
            final String retrain
    ) {
        faceService.deleteFaceByName(faceName, apiKey, retrain);
    }

    @DeleteMapping
    public int deleteFacesByModel(
            @RequestHeader(name = X_FRS_API_KEY_HEADER)
            final String apiKey
    ) {
        return faceService.deleteFacesByModel(apiKey);
    }
}