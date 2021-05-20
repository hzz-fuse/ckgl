package com.hzz.work.cnotroller;

import com.hzz.work.bean.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MaoCnotroller {

    @GetMapping("/")
    public String maozi() {
        return "index";
    }

    @PostMapping("/getData")
    public String showData(int count, int num, Model model) {
        List<Result> list = new ArrayList<>();
        List<Result> listResult = new ArrayList<>();
        if (count == 1) {
            list.add(new Result(2, 1, 1));
        } else if (count == 2) {
            list.add(new Result(1, 2, 1));
            list.add(new Result(2, 3, 1));
        } else if (count == 3) {
            list.add(new Result(1, 1, 2));
            list.add(new Result(2, 3, 5));
            list.add(new Result(1, 2, 3));
            list.add(new Result(2, 1, 3));
        } else {
            int countnum = 3;
            List<Result> list1 = new ArrayList<>();
            list1.add(new Result(1, 2, 1));
            list1.add(new Result(2, 3, 1));
            List<Result> list2 = new ArrayList<>();
            list2.add(new Result(1, 1, 2));
            list2.add(new Result(2, 3, 5));
            list2.add(new Result(1, 2, 3));
            list2.add(new Result(2, 1, 3));
            while (countnum < count) {
                if (countnum % 3 == 0) {
                    List<Result> list3 = new ArrayList<>();
                    for (Result result : list1) {
                        list3.add(new Result(result.getB() + result.getC(), result.getB(), result.getC()));
                    }
                    for (Result result : list2) {
                        list3.add(new Result(result.getB() + result.getC(), result.getB(), result.getC()));
                    }
                    list1 = list2;
                    list2 = list3;
                } else if (countnum % 3 == 1) {
                    List<Result> list3 = new ArrayList<>();
                    for (Result result : list1) {
                        list3.add(new Result(result.getA(), result.getA() + result.getC(), result.getC()));
                    }
                    for (Result result : list2) {
                        list3.add(new Result(result.getA(), result.getA() + result.getC(), result.getC()));
                    }
                    list1 = list2;
                    list2 = list3;
                } else {
                    List<Result> list3 = new ArrayList<>();
                    for (Result result : list1) {
                        list3.add(new Result(result.getA(), result.getB(), result.getB() + result.getA()));
                    }
                    for (Result result : list2) {
                        list3.add(new Result(result.getA(), result.getB(), result.getB() + result.getA()));
                    }
                    list1 = list2;
                    list2 = list3;
                }
                countnum++;
            }
            if (count % 3 == 1) {
                for (Result result : list2) {
                    if (num % result.getA() == 0) {
                        listResult.add(new Result(num, num * result.getB() / result.getA(), num * result.getC() / result.getA()));
                    }
                }
            } else if (count % 3 == 2) {
                for (Result result : list2) {
                    if (num % result.getB() == 0) {
                        listResult.add(new Result(num * result.getA() / result.getB(), num, num * result.getC() / result.getB()));
                    }
                }
            } else {
                for (Result result : list2) {
                    if (num % result.getC() == 0) {
                        listResult.add(new Result(num * result.getA() / result.getC(), num * result.getB() / result.getC(), num));
                    }
                }
            }

//            list=list2;
        }
        if (count <= 3) {
            if (count % 3 == 1) {
                for (Result result : list) {
                    if (num % result.getA() == 0) {
                        listResult.add(new Result(num, num * result.getB() / result.getA(), num * result.getC() / result.getA()));
                    }
                }
            } else if (count % 3 == 2) {
                for (Result result : list) {
                    if (num % result.getB() == 0) {
                        listResult.add(new Result(num * result.getA() / result.getB(), num, num * result.getC() / result.getB()));
                    }
                }
            } else {
                for (Result result : list) {
                    if (num % result.getC() == 0) {
                        listResult.add(new Result(num * result.getA() / result.getC(), num * result.getB() / result.getC(), num));
                    }
                }
            }
        }
        list = listResult;
        model.addAttribute("resultList", list);
        return "index";
    }
}
