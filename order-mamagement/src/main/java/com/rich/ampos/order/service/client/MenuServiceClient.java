package com.rich.ampos.order.service.client;

import com.rich.ampos.order.model.Menu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * The declarative service client of Menu-Management.
 *
 * @author rich
 */
@FeignClient(name="${service.menu}", path="${api.menu}")
public interface MenuServiceClient {

    @GetMapping("/{id}")
    Menu findById(@PathVariable("id") String id);
}
