package com.duoc.subpedido.client;

import com.duoc.subpedido.dto.PpordenDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "pporden-client",
        url = "${pporden.service.url}"
)
public interface PpordenClient {
    @GetMapping("/api/v1/orden/{id}")
    PpordenDTO getPpordenById(@PathVariable("id") Long id);
}