package com.kyueun.apis.route;

import com.kyueun.apis.datamodels.dto.SaleDTO;
import com.kyueun.apis.datamodels.exception.ControllableException;
import com.kyueun.apis.service.SaleService;
import com.kyueun.apis.datamodels.vo.SalePurchaseVO;
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
    public SaleDTO getSale(@PathVariable(value="sale_id") String saleId) throws ControllableException {
        return this.saleService.saleById(Integer.parseInt(saleId));
    }

    @GetMapping("/initialize")
    public void initializeSales() {
        this.saleService.initializeSales();
    }

    @PostMapping("/purchase")
    public void purchase(SalePurchaseVO salePurchaseVO) throws ControllableException {
        int saleId = this.saleService.createSale(salePurchaseVO);
        this.saleService.purchase(saleId);
    }

    @PostMapping("/{sale_id}/refund")
    public void refund(@PathVariable(value="sale_id") String saleId) throws ControllableException{
        this.saleService.refund(Integer.parseInt(saleId));
    }
}