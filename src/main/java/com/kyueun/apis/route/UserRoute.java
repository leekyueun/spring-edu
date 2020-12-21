package com.kyueun.apis.route;

import com.kyueun.apis.datamodels.enumModel.UserGradeEnum;
import com.kyueun.apis.datamodels.UserTotalPaidPrice;
import com.kyueun.apis.model.Sale;
import com.kyueun.apis.model.User;
import com.kyueun.apis.service.SaleService;
import com.kyueun.apis.service.UserService;
import com.kyueun.apis.datamodels.vo.UserRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRoute {
    private final UserService userService;
    private final SaleService saleService;

    @Autowired
    public UserRoute(UserService userService, SaleService saleService) {
        this.userService = userService;
        this.saleService = saleService;
    }

    @GetMapping("")
    @ResponseBody
    public List<User> getUsers() {
        return this.userService.findAll();
    }

    @GetMapping("/{user_id}")
    @ResponseBody
    public User getUser(@PathVariable(value = "user_id") String userId) throws Exception {
        return this.userService.find(Integer.parseInt(userId));
    }

    @PostMapping("")
    public int createUser(UserRegisterVO user) {
        return this.userService.createUser(user);
    }

    @GetMapping("/initialize")
    public void initializeUsers() {
        this.userService.initializeUsers();
    }

    @DeleteMapping("/{user_id}")
    public void deleteUser(@PathVariable(value = "user_id") String userId) {
        this.userService.deleteUser(Integer.parseInt(userId));
    }

    @GetMapping("/user/{user_id}/purchase_list")
    public List<Sale> getuserPurchaseList(@PathVariable(value = "user_id") String userId) {
        return this.saleService.getSalesByUserId(Integer.parseInt(userId));
    }

    @GetMapping("/{user_id}/purchase_amount")
    public UserTotalPaidPrice getuserPurchaseAmount(@PathVariable(value = "user_id") String userId) {
        return this.saleService.getTotalPaidPriceByUserId(Integer.parseInt(userId));
    }

    @GetMapping("/{user_id}/grade")
    public UserGradeEnum getUserGrade(@PathVariable(value = "user_id") String userId) {
        return this.userService.getUserGrade(Integer.parseInt(userId));
    }

}
