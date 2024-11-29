import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
//没做出来的原因：（1）笔试平台不支持跳转源码 （2）日常写码依赖于代码补全和ai (3)错误定位bug为枚举类判定失灵

class Solution {

    //第一题 给输入的信息进行脱敏（错因：char数组转string类型用错方法了）
        public String infoSolve (String input, DateType dateType) {
            char [] strtoCharArray = input.toCharArray();
            if(dateType == DateType.ID_CARD){
                for(int i = 1; i <= 16; i++){
                    strtoCharArray[i] = '*';
                }
            }
            //该死的自动补全啊啊啊！
            String result = new String(strtoCharArray);
            String result1 = Arrays.toString(strtoCharArray);
            System.out.println(result1);
            String result2 = strtoCharArray.toString();
            System.out.println(result2);//如果笔试平台能跳转源码就好了
            //char[] 没有重写 toString()，因此会使用 Object 类中的实现
            //输出 [C@3b9a45b3   C 表示 char[] 类型，后面的哈希值是对象的内存地址的哈希码
            return result;
        }

        //第二题第一问 给出日期和用户id 生成订单号 订单号包括 yyyyMMdd形式的日期+用户id+四位数的序列号（序列号0000-5000循环，每日清零）

        private static final HashMap<String, Integer> orderCountMap = new HashMap<>();
        private static final int MAX_SEQUENCE = 5000;

        public String generateOrder(Date date, String userId){

            //  格式化日期为 "yyyyMMdd"【这个我确实不会】
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String dateKey = dateFormat.format(date);

            // 获取当前日期的订单数
            int sequenceNumber = orderCountMap.getOrDefault(dateKey, 0);

            // 如果序列号已达上限，重置为 0
            if (sequenceNumber >= MAX_SEQUENCE) {
                sequenceNumber = 0;
            }

            //增加当前日期的订单数
            orderCountMap.put(dateKey, sequenceNumber + 1);

            //格式化序列号为四位数字，不足补 0【这个也不会】
            String sequenceString = String.format("%04d", sequenceNumber);

            //拼接订单号：日期 + UserId + 序列号
            return dateKey + userId + sequenceString;

        }


    //第二题第二问 拿到订单号 返回Order类型对象
        public Order creatOrder(String input) {

            Order order = new Order();
            //先判定这个input是否合法 如果不是16位则非法（可能判断逻辑要稍微更复杂一点
            if(input.length() != 18){
                throw new IllegalArgumentException("输入必须是18个字符长");
            }
             String serialNumber = input.substring(0,4); //这里调错方法了 调成spilt了。。。 以及注意substring是左闭右开的！！
             String dateyearString = input.substring(4,8);
             String datemonthString = input.substring(8,10);
             String datedayString = input.substring(10,12);
             String userId = input.substring(12,18);

             Date date = new Date();

             //这玩意我也不会啊！！！平常遇到了都是现查的
            String dateString = dateyearString + "-" + datemonthString + "-" + datedayString;
            LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

             order.setSerialNumber(serialNumber);
             order.setCreateDate(java.sql.Date.valueOf(localDate)); //这个Date类型不好设置啊
             order.setUserId(userId);

             return order;

        }



    public static void main(String[] args){
        Solution solution = new Solution();
        String res = solution.infoSolve("430521200009080058",DateType.ID_CARD);
        System.out.println(res);

        String res1 = solution.generateOrder(new Date(),"TOM123");
        System.out.println(res1);

        Order order = solution.creatOrder("123420241129TOM123");
        System.out.println(order);
    }
}



