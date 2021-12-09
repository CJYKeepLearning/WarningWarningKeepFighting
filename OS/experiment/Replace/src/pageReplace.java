import java.util.Scanner;

public class pageReplace {
    static int[] page;
    static int[] array;
    static int result=0;
    public static  void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入页框数量");
        int n1 = sc.nextInt();
        page = new int[n1];
        System.out.println("选择FIFO算法请输入1，选择LRU算法请输入2，选择OPT算法请输入3");
        int choose = sc.nextInt();
        System.out.println("请输入用户访问页地址顺序的总个数");
        int n2 = sc.nextInt();
        array = new int[n2];
        System.out.println("请输入用户访问页地址顺序");
        if (choose==3){
            for (int i=0;i<array.length;i++){
                array[i] = sc.nextInt();
            }
            replaceOPT();
        }else {
            //使用LRU或者FIFO算法
            for (int i=0;i<array.length;i++){
                array[i] = sc.nextInt();
                if (search(array[i])){
                    System.out.println(array[i]+"：命中");
                }else {
                    result++;
                    System.out.println(array[i]+"：缺页");
                    switch (choose){
                        case 1:
                            replaceFIFO(array[i]);           //FIFO
                            break;
                        case 2:
                            replaceLRU(array[i],i);
                    }
                }
            }
        }
        System.out.println("共缺页:"+result+"次！");
    }
    //OPT算法
    private static void replaceOPT() {
        for (int i=0;i<array.length;i++){
            if (search(array[i])){
                System.out.println(array[i]+"：命中");
            }else {
                result++;
                System.out.println(array[i]+"：缺页");
                LackOPT(array[i],i);
            }
        }
    }

    private static void LackOPT(int m, int index) {
        for (int i=0;i<page.length;i++){
            if (page[i] == 0){
                page[i] = m;
                return;
            }
        }
        int max = 0;
        for (int i=0;i<page.length;i++){
            int n = page[i];
            max = Math.max(max,searchFromArrayOPT(n,index));
        }
        replaceNumInPage(m,max);
    }

    private static int searchFromArrayOPT(int m, int index) {
        for (int i=index+1;i<array.length;i++){
            if (array[i]==m){
                return i;
            }
        }
        return 0;
    }

    //LRU算法
    private static void replaceLRU(int m, int index) {
        //初始时的缺页
        for (int i=0;i<page.length;i++){
            if (page[i]==0){
                page[i] = m;
                return;
            }
        }
        //当栈满了之后的缺页
        int min = array.length;
        for (int i=0;i<page.length;i++){
            int n = page[i];
            min = Math.min(min,searchFromArrayLRU(n,index));
        }
        replaceNumInPage(m,min);
    }

    private static void replaceNumInPage(int m, int min) {
        for (int i=0;i<page.length;i++){
            if (page[i]==array[min]){
                System.out.println("将"+page[i]+"替换成"+m);
                page[i] = m;
            }
        }
    }

    //寻找命中过的数在array中的坐标
    private static int searchFromArrayLRU(int m, int index) {
        for (int i=index;i>=0;i--){
            if (array[i]==m){
                return i;
            }
        }
        return 0;
    }

    //FIFO算法
    private static void replaceFIFO(int m) {
        //初始时的缺页
        for (int i=0;i<page.length;i++){
            if (page[i]==0){
                page[i] = m;
                return;
            }
        }
        //当栈满了之后的缺页
        System.out.println("将"+page[0]+"替换成"+m);
        for (int i=0;i<page.length-1;i++){
            page[i]=page[i+1];//先进先出
        }
        page[page.length-1] = m;
    }

    //返回false时为缺页。
    private static boolean search(int m) {
        boolean flag = false;
        for (int i=0;i<page.length;i++){
            if (page[i]==m) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
