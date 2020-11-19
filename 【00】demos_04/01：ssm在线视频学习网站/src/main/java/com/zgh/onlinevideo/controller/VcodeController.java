package com.zgh.onlinevideo.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.zgh.onlinevideo.dto.ResponseResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@SessionAttributes({"session_vcode"})
public class VcodeController {

    @RequestMapping("/checkVcode")
    @ResponseBody
    public ResponseResult checkVcode(String vcode, HttpSession session) {

        ResponseResult result = new ResponseResult(1, "ok");

        String session_vcode = (String) session.getAttribute("session_vcode");

        if (StrUtil.isEmpty(session_vcode) || StrUtil.isEmpty(vcode) || !session_vcode.equals(vcode)) {
            result.setRcode(-1);
            result.setMessage("validationCode error");
        }

        return result;
    }

    char[] vcodeSequence = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    // 响应验证码 二进制图片
    //      http协议设置响应的类型
    @RequestMapping("/vcode")
    public void vcode(HttpServletResponse response, Model model) {

        // 设置生成图片的属性
        BufferedImage bufferedImage = new BufferedImage(80, 30, BufferedImage.TYPE_INT_RGB);

        Graphics2D gd = bufferedImage.createGraphics();

        gd.setColor(Color.WHITE);

        gd.drawRect(0, 0, 80, 30);

        gd.setFont(new Font("宋体", Font.BOLD, 25));

        // 保存验证码
        StringBuilder builder = new StringBuilder();

        // 生成随机验证码 4个数字
        for (int i = 0; i < 4; i++) {
            String rdStr = String.valueOf(vcodeSequence[RandomUtil.randomInt(10)]);

            builder.append(rdStr);

            // rgb
            gd.setColor(new Color(RandomUtil.randomInt(80, 255), RandomUtil.randomInt(80, 255), RandomUtil.randomInt(80, 255)));

            // 数字坐标
            // 15,25
            // 30,25
            // 45,25
            // 60,25
            gd.drawString(rdStr, (i + 1) * 15, 25);
        }

        // 保存验证码 > session 类上注解
        model.addAttribute("session_vcode", builder.toString());

        // 设置HTTP协议响应头
        HttpHeaders httpHeaders = new HttpHeaders();

        // 图片类型
        httpHeaders.setContentType(MediaType.IMAGE_JPEG);

        // 不使用缓存
        //      Cache-Control:no-cache
        //      Pragma:no-cache
        //      Expires：-1
        httpHeaders.setCacheControl("no-cache");
        httpHeaders.setPragma("no-cache");
        httpHeaders.setDate("expires", -1);

        //输出流
        try {
            ServletOutputStream stream = response.getOutputStream();

            ImageIO.write(bufferedImage, "jpeg", stream);

            stream.flush();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
