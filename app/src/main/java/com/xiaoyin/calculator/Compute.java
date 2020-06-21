package com.xiaoyin.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;

    public class Compute {

        /**
         * @param args
         */
        public static void main(String[] args) {
            String data = "100+3+2-200+(10000/5+(100/2))";
            String[] dataArray = getDataArray(data);
            String[] postfixArray = getPostFix(dataArray);
            System.out.println(computeWithPostFix(postfixArray));
        }

        public static String[] getDataArray(String data) {
            if (data == null || "".equals(data.trim()) || data.trim().length() < 1) {
                return null;
            }
            int dataLength = data.length();
            String[] dataArray = new String[dataLength];// 根据字符串的长度创建数组
            int top = 0;
            String numStr = "";
            for (int i = 0; i < dataLength; i++) {
                char datai = data.charAt(i);
                String num = String.valueOf(datai);
                if (isNum(num)) {// 数字
                    numStr += num;
                    if (i + 1 == dataLength) {
                        dataArray[top] = numStr;
                        top++;
                    }
                } else {
                    if (!"".equals(numStr)) {// numStr 存在
                        dataArray[top] = numStr;// 数字存放到结果数组中
                        numStr = "";// 还原
                        top++;// 指针下移
                    }
                    // 符号
                    dataArray[top] = num;
                    top++;// 指针下移
                }
            }
            return removeNull(dataArray, top);
        }

        /**
         * 获取后缀表达式
         *
         * @param
         * @return
         */

        public static String[] getPostFix(String[] dataArray) {
            if (dataArray == null || dataArray.length < 1) {
                return null;
            }
            // 1.遍历数组 数字输出
            // 2.若是符号 则判断和栈顶符号的优先级 是右括号 或者优先级低于栈顶符号(乘除高于加减)则栈顶元素依次出栈 输出 并将当前符号入栈
            // 3.数组结束 栈内符号依次出栈
            int dataLength = dataArray.length;
            Stack<String> dataStack = new Stack<String>();
            String[] dataBuffer = new String[dataLength];
            int top = 0;
            for (int i = 0; i < dataLength; i++) {
                String datai = dataArray[i];
                if (isNum(datai)||datai.equals(".")) {// 数字

                            dataBuffer[top] = datai;
                            top++;// 指针下移



                } else if (isLeftBracket(datai)) {// 左括号
                    dataStack.push(datai);// 压栈
                } else if (ComputeEnum.isCompute(datai)) {// 运算符
                    List<String> lessThenMeList = getNotLessThenMeta(dataStack,
                            datai);
                    if (lessThenMeList != null && !lessThenMeList.isEmpty()) {
                        for (String lessThen : lessThenMeList) {// 小于当前运算符的符号输出
                            dataBuffer[top] = lessThen;
                            top++;// 指针下移
                        }
                    }
                    dataStack.push(datai);// 当前元素入栈
                } else if (isRightBracket(datai)) {// 右括号 查找到最近左括号之间的所有符号 出栈
                    List<String> betweenLeftBracketList = getBetweenLeftBracketMeta(dataStack);
                    if (betweenLeftBracketList != null
                            && !betweenLeftBracketList.isEmpty()) {
                        for (String between : betweenLeftBracketList) {// 小于当前运算符的符号输出
                            dataBuffer[top] = between;
                            top++;// 指针下移
                        }
                    }
                } else {
                    System.err.println("请注意中英文符号,检查出包含不支持的运算符！");
                    return null;
                }
            }
            while (!dataStack.isEmpty()) {
                dataBuffer[top] = dataStack.pop();
                top++;// 指针下移
            }
            return removeNull(dataBuffer, top);
        }

        // 根据后缀表达式计算出结果 并打印
        public static String computeWithPostFix(String[] postfixArray) {
            if (postfixArray == null || postfixArray.length < 1) {
                System.err.println("postfixArray is null !");
                return null;
            }
            // 1.遇到数字则入栈
            // 2.遇到运算符号 则将栈顶两元素出栈
            // 3.将运算结果入栈
            // 4.数组遍历结束 将栈顶元素 取出
            Stack<String> stack = new Stack<String>();
            for (String meta : postfixArray) {
                if (isNum(meta)) {// 数字
                    stack.push(meta);
                } else if (ComputeEnum.isCompute(meta)) {// 运算符号
                    double pop = Double.parseDouble(stack.pop());
                    double popNext = Double.parseDouble(stack.pop());
                    double result = compute(pop, popNext, meta);
                    stack.push(String.valueOf(result));
                }
            }
            //System.out.println("运算结果为：" + stack.pop());
            return stack.pop();
        }

        public static double compute(double pop, double popNext, String meta) {
            double result = 0;
            ComputeEnum compute = ComputeEnum.get(meta);
            switch (compute) {
                case plus:// 加
                    result = popNext + pop;
                    break;
                case minus:// 减
                    result = popNext - pop;
                    break;
                case multiply:// 乘
                    result = popNext * pop;
                    break;
                case divide:// 除
                    if ((pop < 0.000000001) && (pop > -0.000000001)) {
                        System.err.println("被除数不能为0！");
                        break;
                    }
                    result = popNext / pop;
                    break;
            }
            return result;
        }

        public static List<String> getBetweenLeftBracketMeta(
                Stack<String> dataStack) {
            if (dataStack == null || dataStack.size() < 1) {
                return null;
            }
            List<String> list = new ArrayList<String>(dataStack.size());
            while (!dataStack.isEmpty()) {
                String pop = dataStack.pop();// 栈顶元素出栈
                if (isLeftBracket(pop)) {
                    break;
                }
                list.add(pop);
            }
            return list;
        }

        /**
         * 取出所有不比自己优先级低的元素
         *
         * @param dataStack
         * @param datai
         * @return
         */
        public static List<String> getNotLessThenMeta(Stack<String> dataStack,
                                                       String datai) {
            if (dataStack == null || dataStack.size() < 1) {
                return null;
            }
            ComputeEnum computei = ComputeEnum.get(datai);
            List<String> list = new ArrayList<String>(dataStack.size());
            while (!dataStack.isEmpty()) {
                String pop = dataStack.peek();// 栈顶元素
                ComputeEnum compute = ComputeEnum.get(pop);
                if (compute == null) {
                    break;
                }
                if (compute.level < computei.level) {
                    break;
                } else {// 优先级高于当前符号 出栈
                    dataStack.pop();
                    list.add(pop);
                }
            }
            return list;
        }

        public static String[] removeNull(String[] dataArray, int size) {
            String[] dataResult = new String[size];
            System.arraycopy(dataArray, 0, dataResult, 0, dataResult.length);
            return dataResult;
        }

        public static boolean isNum(String num) {
            String reg = "^\\d+$";
            return Pattern.compile(reg).matcher(num).find();
        }

        /**
         * 左括号
         *
         * @param num
         * @return
         */
        public static boolean isLeftBracket(String num) {
            return "(".equals(num);
        }

        /**
         * 右括号
         *
         * @param num
         * @return
         */
        public static boolean isRightBracket(String num) {
            return ")".equals(num);
        }
    }

    enum ComputeEnum {
        plus("+", 0), // 加法
        minus("-", 0), // 减法
        multiply("*", 1), // 乘法
        divide("/", 1), // 除法
        ;

        private static Map<String, ComputeEnum> dataMap = new HashMap<String, ComputeEnum>();
        static {
            dataMap.put(plus.code, plus);
            dataMap.put(minus.code, minus);
            dataMap.put(multiply.code, multiply);
            dataMap.put(divide.code, divide);
        }
        public String code;

        public int level;

        private ComputeEnum(String code, int level) {
            this.code = code;
            this.level = level;
        }

        public static ComputeEnum get(String code) {
            return dataMap.get(code);
        }

        public static boolean isCompute(String code) {
            return dataMap.containsKey(code);
        }
    }

