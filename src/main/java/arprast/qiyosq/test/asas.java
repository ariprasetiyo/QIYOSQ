package arprast.qiyosq.test;

public class asas {
    public int solution(int[] a) {
        if (isAesthetically(a)) {
            return 0;
        }

        int aestheticCount = 0;
        for (int j = 0; j < a.length; j++) {
            int[] newA = copyWithoutAnElement(a, j);
            if (isAesthetically(newA)) {
                aestheticCount++;
            }
        }

        if (aestheticCount == 0) {
            return -1;
        } else {
            return aestheticCount;
        }
    }

    private int[] copyWithoutAnElement(int[] arrayInput, int indexOfRemoved) {
        int arrayLength = arrayInput.length;
        int[] newArr = new int[arrayLength - 1];
        int tempK = 0;
        for (int k = 0; k < arrayLength; k++) {
            if (k != indexOfRemoved) {
                newArr[tempK++] = arrayInput[k];
            }
        }
        return newArr;
    }

    private boolean isAesthetically(int[] inputData) {
        int newArrayLenght = inputData.length;
        int flag = 0;
        for (int i = 0; i < newArrayLenght; i++) {
            if (flag == 0) {
                if (inputData[i] < inputData[i + 1]) {
                    flag = 1;
                } else {
                    flag = 2;
                }
            } else {
                if (flag == 1) {
                    if (i % 2 == 1 && inputData[i] > inputData[i - 1]) {
                    } else if (i % 2 == 0 && inputData[i] < inputData[i - 1]) {
                    } else {
                        return false;
                    }
                } else {
                    if (i % 2 == 1 && inputData[i] < inputData[i - 1]) {
                    } else if (i % 2 == 0 && inputData[i] > inputData[i - 1]) {
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        asas solutionObj = new asas();
        int[] a1 = {1, 3, 1, 2};
        int[] a2 = {3, 4, 5, 3, 7};
        int[] a3 = {1, 2, 4, 4, 2, 5};
        System.out.println(solutionObj.solution(a1));
        System.out.println(solutionObj.solution(a2));
        System.out.println(solutionObj.solution(a3));
    }
}



 - ON US : netzme - toko netzme. tanpa rekon, settlement automatic by scheduler
 - OFF US :  Rekening netzme ke rekning lain via JALIN. h+1 ready TO settle, h+2 settlement
         Flow :
         - reconsile di dpt dari jalin bisa via email / SFTP. dari Gofar merubaha flag ready  TO test setelah itu upload ke web jajang
            - sukses dan refund
            - suspect ( tanda ? di jalin apakah berhasil ato blm ( gantung ), di kita di anggap berhasil ). masih menunggu konfirmasi pak noval utk kepastiaanya perlakuan supect jika status menjadi gagal


         note
         - ngasih ( keluar ). settlement manual,  jam 12 max , jalin ke kita, settle utk tiap transaski
         - dapat ( masuk ). settlement automatic, max jam 2 siang  kita ke jalin, settle utk tiap transaski
         - Ada netting yg perlu di tranfer manuak ke gofar, yaitu jika kita akan
            - NGASIH : kita ke luar 3 jt, luar ke kita 1 jt. dana sudah tersedia di netzme. transfer ke jalin 2 jt.
            - DAPAT : kita keluar 1 jt, luar ke kita ke 3 jt. maka kita ada dana masuk 2 jt