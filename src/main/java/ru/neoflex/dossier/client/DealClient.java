package ru.neoflex.dossier.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.neoflex.dossier.model.ApplicationDTO;

@FeignClient(url = "${client.deal.endpoint}", name = "DEAL-FEIGN-CLIENT")
public interface DealClient {
    @GetMapping("/application/admin/{applicationId}")
    ResponseEntity<ApplicationDTO> getApplicationById(@PathVariable Long applicationId);

    @PutMapping("/application//admin/{applicationId}/status")
    void updateApplicationStatusById(@PathVariable Long applicationId, @RequestParam String statusName);

}
