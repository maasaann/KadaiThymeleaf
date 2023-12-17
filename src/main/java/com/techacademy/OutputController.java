package com.techacademy;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OutputController {

    @PostMapping("/calc")
    public String postOutput(
            @RequestParam("val_a") String val1Str,
            @RequestParam("val_b") String val2Str,
            @RequestParam("val_c") String val3Str,
            Model model) {
        model.addAttribute("val_a", val1Str);
        model.addAttribute("val_b", val2Str);
        model.addAttribute("val_c", val3Str);

        // 文字列が数字に変換可能かを確認
        int val1, val2, val3;
        try {
            val1 = Integer.parseInt(val1Str);
            val2 = Integer.parseInt(val2Str);
            val3 = Integer.parseInt(val3Str);
        } catch (NumberFormatException e) {
            model.addAttribute("res", "エラー：数字を入力してください");
            return "calc";
        }

        // 長い順に並べ替える val_1 < val_2 < val_3 に格納
        // 後の計算をシンプルにするため
        int val_1, val_2, val_3;
        if (val1 >= val2 && val1 >= val3) {
            val_3 = val1;
            val_2 = (val2 >= val3) ? val2 : val3;
            val_1 = (val2 >= val3) ? val3 : val2;
        } else if (val2 >= val1 && val2 >= val3) {
            val_3 = val2;
            val_2 = (val1 >= val3) ? val1 : val3;
            val_1 = (val1 >= val3) ? val3 : val1;
        } else {
            val_3 = val3;
            val_2 = (val1 >= val2) ? val1 : val2;
            val_1 = (val1 >= val2) ? val2 : val1;
        }

        //三角形の種別判定
        String res;
        if ( val_1 + val_2 <= val_3) {
            res = "エラー：三角形として成立しません";
        } else if ( val_1 == val_2 && val_2 == val_3) {
            res = "正三角形";
        } else if ( val_1 == val_2 || val_2 == val_3 ) {
            res = "二等辺三角形";
        } else {
            res = "不等辺三角形";
        }

        //結果を格納
        model.addAttribute("res", res);

        return "calc";
    }
}