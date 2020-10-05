package controller;

import domain.Person;
import mymvc.ModelAndView;
import mymvc.RequestMapper;
import mymvc.SessionAttributes;

/**
 *  1.没有继承关系 降低类和类之间的耦合关系
 *  2.底层还保留了原有Controller的管理机制 (单例 懒加载)
 *  3.没有方法重写 方法名字随意 少了很多约束 使用更加灵活
 *  4.有了最终的转发或重定向 只使用一个简单的String作为返回值
 *  5.减少了方法的参数使用 request response
 *  6.方法的异常没有啦
 *  7.创建一个容器 ModelAndView 实现存值
 */

@RequestMapper("AtmController.do")
@SessionAttributes("person")
public class AtmController {

    //参数前注解值 == 请求携带的参数（如果有）
    public ModelAndView login(Person person) {

        //想要带走数据
        //还需要框架帮忙进行请求转发、重定向

        ModelAndView mv = new ModelAndView();
        mv.setAttribute("person", person);
        mv.setViewName("redirect:welcome.jsp");

        return mv;

        //处理转发与重定向
        //1.直接给予响应 response.getWriter().write("")
        //  返回值类型为void，或者返回“”、“null”
        //2.请求转发 => 交给框架
        //  直接返回 “资源名”
        //3.请求重定向 => 交给框架
        //  返回 “redirect:资源名”
    }

}
