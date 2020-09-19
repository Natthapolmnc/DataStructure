import java.util.Arrays;

class Sort {
    public static void quickSort(int[] data) {
        quickSplit(data, 0, data.length - 1);
    }

    public static void mergeSort(int[] data) {
        mergeSplit(data, 0, data.length - 1);
    }

    public static void mergeSplit(int[] data, int start, int stop) {
        if (start == stop) {
            return;
        } else {
            int splitPoint = (stop + start) / 2;
            mergeSplit(data, start, splitPoint);
            mergeSplit(data, splitPoint + 1, stop);
            merge(data, start, splitPoint + 1, stop);
        }

    }

    public static void merge(int[] data, int frtStart, int lstStart, int lstStop) {
        int p = 0;
        int start = frtStart;
        int splitPoint = lstStart - 1;
        int size = lstStop - frtStart + 1;
        int[] temp = new int[data.length];
        while (start <= splitPoint && lstStart <= lstStop) {
            if (data[start] >= data[lstStart]) {
                temp[p++] = data[start++];
            } else {
                temp[p++] = data[lstStart++];
            }
        }
        while (start <= splitPoint) {
            temp[p++] = data[start++];
        }
        while (lstStart <= lstStop) {
            temp[p++] = data[lstStart++];
        }

        for (int j = 0; j < size; j++) {
            data[frtStart + j] = temp[j];
        }
    }

    public static void quickSplit(int[] data, int start, int finish) {
        int pivot = data[start];
        int first = start + 1;
        int last = finish;
        while (first <= last) {
            if (data[first] > pivot) {
                first++;
            } else if (data[first] <= pivot) {
                if (data[last] <= pivot) {
                    last--;
                } else if (data[last] > pivot) {
                    int temp = data[first];
                    data[first] = data[last];
                    data[last] = temp;
                }
            }
        }
        data[start] = data[last];
        data[last] = pivot;
        if (last - 1 > start) {
            quickSplit(data, start, last - 1);
        }
        if (first < finish) {
            quickSplit(data, first, finish);
        }
    }

    public static void main(String[] args) {
        int[] quickData = { 15, 9, 7, 16, 31, 2, 20, 25, 17, 12 };
        int[] mergeData = { 5, 2, 12, 9, 1, 8, 7, 18 };
        quickSort(quickData);
        mergeSort(mergeData);
        System.out.println(Arrays.toString(quickData));
        System.out.println(Arrays.toString(mergeData));
    }
}