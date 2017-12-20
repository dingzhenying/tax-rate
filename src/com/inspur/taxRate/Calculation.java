package com.inspur.taxRate;

/**
 * 修改年终奖计算
 * 不再传入拆分次数
 * 自动计算最佳拆分方法
 * 返回最佳拆分方法的详细信息
 */

public class Calculation {
    /**
     * 定义区间，每个区间的税率和每个区间的速算扣除数
     */
    int[] interval = {0, 1500, 4500, 9000, 35000, 55000, 80000};
    double[] rate = {0.03, 0.1, 0.2, 0.25, 0.3, 0.35, 0.45};
    int[] deductions = {0, 105, 555, 1005, 2755, 5505, 13505};

    /**
     * 计算月工资
     *
     * @param monthWage
     * @return
     */
    public double month(double monthWage) {
        double monthRatePath = monthWage - 3500;
        double result = 0;
        if (monthRatePath < interval[0]) {
            result = monthWage;
        } else if (monthRatePath > interval[interval.length - 1]) {
            monthRatePath = monthRatePath * rate[rate.length - 1] - deductions[deductions.length - 1];
            result = monthWage - monthRatePath;
        } else {
            for (int i = 0; i < interval.length; i++) {
                for (int j = 1; j <= interval.length; j++) {
                    if (interval[i] < monthRatePath && monthRatePath <= interval[j]) {
                        monthRatePath = monthRatePath * rate[i] - deductions[i];
                        result = monthWage - monthRatePath;
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 计算年终奖
     * 根据拆分的参数调用下面的方法
     * @return Map
     * 会进行三次计算，取最大值
     * 返回的map中包含
     * 结果，拆分后的年终奖，拆分的第一个月
     */
    public String year(double monthWage, double yearAwards,int splitWay) {

        String result="";
        switch (splitWay){
            case 1:
                result=splitOne(monthWage,yearAwards);
                break;
            case 2:
                result=splitTwo(monthWage,yearAwards);
                break;
            case 0:
                result="税后奖金为"+noSplit(monthWage,yearAwards);
                break;
            default:
                break;
        }

        return result;
    }

    /**
     * 年终奖不拆分
     */
    public double noSplit(double monthWage, double yearAwards) {
        double result = 0;
        double quotient = yearAwards / 12;
        double contentRate = 0;
        int contentDeductions = 0;
        int startRate=3500;
        //确定税率和速扣数
        if (quotient < interval[0]) {
            contentRate = 0;
            contentDeductions = 0;
        } else if (quotient > interval[interval.length - 1]) {
            contentRate = 0.45;
            contentDeductions = 13505;
        } else {
            for (int i = 0; i < interval.length; i++) {
                for (int j = 1; j <= interval.length; j++) {
                    if (interval[i] < quotient && quotient <= interval[j]) {
                        contentRate = rate[i];
                        contentDeductions = deductions[i];
                        break;
                    }
                }
            }
        }
        //根据税率和速扣数进行计算
        if (monthWage > startRate) {
            result = yearAwards - (yearAwards * contentRate - contentDeductions);
        } else if (monthWage <= startRate) {
            result = yearAwards - ((yearAwards - (3500 - monthWage)) * contentRate - contentDeductions);
        }
        return result;
    }

    /**
     * 年终奖拆分一个月
     */
    public String splitOne(double monthWage, double yearAwards) {
        double result;
        double max = 0;
        int first = 0;
        for (int i = 1; i < yearAwards; i++) {
            double monthWageFirst =monthWage + i;
            double afterYearAwards = yearAwards - i;
            result = noSplit(monthWage, afterYearAwards);
            //每一次向月拆分，税后工资都会发生改变，这部分由于拆分改变的数额加上拆分后年终奖的税额就是整体的税后奖金
            result = month(monthWageFirst)-month(monthWage)+result;
            //取最大值和拆分的数
            if (result > max) {
                max = result;
                first=i;
            }
        }
        return "拆分到第一个月"+first+"元，会得到最大收益，税后奖金为"+max;
    }

    /**
     * 年终奖按两个月拆
     */
    public String splitTwo(double monthWage, double yearAwards) {
        double month1;
        double month2;
        double result;
        double max=0;
        int first = 0;
        int second = 0;
        for (int i=1;i<yearAwards;i++){
            double monthWageFirst = monthWage + i;
            double firstYearAwards = yearAwards - i;
            month1 =month(monthWageFirst)-month(monthWage);
            for (int j=1;j<firstYearAwards;j++){
                double monthWageSecond =monthWage + j;
                double secondYearAwards = firstYearAwards - j;
                month2= month(monthWageSecond)-month(monthWage);
                result=noSplit(monthWage,secondYearAwards) +month1+month2;
                //取最大值和两次拆分的数
                if (result >max){
                    max=result;
                    first=i;
                    second=j;
                }
            }
        }
        return "拆分到第一个月"+first+"元，第二个月"+second+"元，会得到最大收益，税后奖金为"+max;
    }
}

