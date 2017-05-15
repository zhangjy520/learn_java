/**
 * Created by Administrator on 2017/4/12.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("aaaaabbbccc".endsWith("bccc"));
    }

    public static void maopao() {
        int count = 0;
        int[] arr = {9,3,1,4,7,2,6};
        for (int i = 0; i < arr.length; i++) {

            for (int j = i; j < arr.length - i - 1; j++) {
                int temp;
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
                int k = arr.length-1-j;
                if (k > 0){
                    if (arr[k] < arr[k-1] ){
                        temp = arr[k - 1];
                        arr[k - 1] = arr[k];
                        arr[k] = temp;
                    }
                    k--;
                }
                count++;
                System.out.println("进行第"+count+"次循环");
            }
        }
    }

    public static void fastBubble(int[] data){
        long startTime,endTime;
        int end = data.length-1;
        int tmpl;
        int start = 0;
        int minCur,maxCur;
        startTime = System.nanoTime();
        while(start < end-1){
            minCur = start;
            maxCur = end;
            for(int mi=start,mx=end;mx-mi>=0;mi++,mx--){
                if(data[mi] > data[mx]){
                    tmpl = data[mi];
                    data[mi] = data[mx];
                    data[mx] = tmpl;
                }
                if(data[mi]<data[minCur]) minCur = mi;
                if(data[mx]>data[maxCur]) maxCur = mx;
            }
            if(minCur != start){
                tmpl = data[minCur];
                data[minCur] = data[start];
                data[start] = tmpl;
            }
            if(maxCur != end){
                tmpl = data[maxCur];
                data[maxCur] = data[end];
                data[end] = tmpl;
            }
            start++;
            end--;
        }
        endTime = System.nanoTime();


     /*   System.out.print("排序后是:");
        for(int va:data){
            System.out.print(va+" ");
        }
        System.out.println();
        System.out.println("排序使用时间："+(endTime-startTime));*/
    }

    public static void zheBan(double[] arr, double pa) {
        int begin = 0;
        int end = arr.length - 1;
        int count = 0;
        while (begin <= end) {
            count++;
            int middle = (begin + end) / 2;

            if (pa > arr[middle]) {
                begin = middle + 1;
            } else if (pa < arr[middle]) {
                end = middle - 1;
            } else if (pa == arr[middle]) {
                System.out.println("目标索引为" + middle);
                break;
            }

            System.out.println("第" + count + "次查询");
            if (begin > end) {
                System.out.println("未找到");
            }
        }


    }


}
