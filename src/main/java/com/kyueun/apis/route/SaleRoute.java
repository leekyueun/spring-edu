package com.kyueun.apis.route;

import com.kyueun.apis.model.Sale;
import com.kyueun.apis.service.SaleService;
import com.kyueun.apis.vo.SalePurcheseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sale")
public class SaleRoute {
    private final SaleService saleService;

    @Autowired
    public SaleRoute(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/{sale_id}")
    @ResponseBody
    public Sale getSale(@PathVariable(value = "sale_id") String saleId) throws Exception {
        return this.saleService.find(Integer.parseInt(saleId));
    }

    @GetMapping("/initialize")
    public void initializeSales() {
        this.saleService.initializeSales();
    }

    @PostMapping("/purchase")
    public void purchase(SalePurcheseVO salePurcheseVO) throws  Exception {
        int saleId = this.saleService.createSale(salePurcheseVO);
        this.saleService.purchase(saleId);
    }

    @PostMapping("/{sale_id}/refund")
    public void refund(@PathVariable(value = "sale_id") String saleId) throws Exception {
        this.saleService.refund(Integer.parseInt(saleId));
    }
}