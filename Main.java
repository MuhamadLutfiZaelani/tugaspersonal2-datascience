import java.util.*;

public class Main {
    // ====== Array Operations ======
    static class ArrayOperations {
        // Traversal
        public static void traverse(int[] arr, int length) {
            System.out.print("[");
            for (int i = 0; i < length; i++) {
                System.out.print(arr[i]);
                if (i != length - 1) System.out.print(", ");
            }
            System.out.println("]");
        }

        // Linear search
        public static int linearSearch(int[] arr, int length, int target) {
            for (int i = 0; i < length; i++) {
                if (arr[i] == target) return i;
            }
            return -1;
        }

        // Binary search (array harus urut)
        public static int binarySearch(int[] arr, int length, int target) {
            int left = 0, right = length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (arr[mid] == target) return mid;
                else if (arr[mid] < target) left = mid + 1;
                else right = mid - 1;
            }
            return -1;
        }

        // Insert value pada index tertentu
        public static int insert(int[] arr, int length, int value, int index) {
            if (length >= arr.length || index < 0 || index > length) {
                System.out.println("Gagal insert: Index di luar batas.");
                return length;
            }
            System.arraycopy(arr, index, arr, index + 1, length - index);
            arr[index] = value;
            return length + 1;
        }

        // Delete value pada index tertentu
        public static int delete(int[] arr, int length, int index) {
            if (index < 0 || index >= length) {
                System.out.println("Gagal delete: Index di luar batas.");
                return length;
            }
            System.arraycopy(arr, index + 1, arr, index, length - index - 1);
            return length - 1;
        }
    }

    // ====== ArrayList Operations ======
    static class ArrayListOperations {
        public static void traverse(ArrayList<Integer> list) {
            System.out.println(list);
        }

        public static void add(ArrayList<Integer> list, int value, int index) {
            if (index < 0 || index > list.size()) {
                System.out.println("Gagal add: Index di luar batas.");
            } else {
                list.add(index, value);
            }
        }

        public static void delete(ArrayList<Integer> list, int index) {
            if (index < 0 || index >= list.size()) {
                System.out.println("Gagal delete: Index di luar batas.");
            } else {
                list.remove(index);
            }
        }

        public static int search(ArrayList<Integer> list, int target) {
            return list.indexOf(target);
        }

        public static void sort(ArrayList<Integer> list) {
            Collections.sort(list);
        }
    }

    // ====== Main Program (Comparison + Output) ======
    public static void main(String[] args) {
        // Data awal
        int[] arr = new int[1000];
        int arrLength = 5;
        arr[0] = 10; arr[1] = 20; arr[2] = 30; arr[3] = 40; arr[4] = 50;

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < arrLength; i++) list.add(arr[i]);

        // Traversal
        System.out.print("Array Traversal: ");
        ArrayOperations.traverse(arr, arrLength);
        System.out.print("ArrayList Traversal: ");
        ArrayListOperations.traverse(list);

        // Search
        int target = 30;
        long t1 = System.nanoTime();
        int arrIdx = ArrayOperations.linearSearch(arr, arrLength, target);
        long t2 = System.nanoTime();
        System.out.println("Pencarian 30 dalam Array: " + (arrIdx >= 0 ? "Ditemukan di indeks " + arrIdx : "Tidak ditemukan"));

        long t3 = System.nanoTime();
        int listIdx = ArrayListOperations.search(list, target);
        long t4 = System.nanoTime();
        System.out.println("Pencarian 30 dalam ArrayList: " + (listIdx >= 0 ? "Ditemukan di indeks " + listIdx : "Tidak ditemukan"));

        // Insert 25 di posisi ke-2
        arrLength = ArrayOperations.insert(arr, arrLength, 25, 2);
        ArrayListOperations.add(list, 25, 2);
        System.out.print("Array setelah penyisipan elemen 25: ");
        ArrayOperations.traverse(arr, arrLength);
        System.out.print("ArrayList setelah penyisipan elemen 25: ");
        ArrayListOperations.traverse(list);

        // Delete elemen index ke-3
        arrLength = ArrayOperations.delete(arr, arrLength, 3);
        ArrayListOperations.delete(list, 3);
        System.out.print("Array setelah delete index 3: ");
        ArrayOperations.traverse(arr, arrLength);
        System.out.print("ArrayList setelah delete index 3: ");
        ArrayListOperations.traverse(list);

        // Sort (untuk ArrayList saja)
        ArrayListOperations.sort(list);
        System.out.print("ArrayList setelah di-sort: ");
        ArrayListOperations.traverse(list);

        // Waktu eksekusi pencarian
        System.out.printf("Waktu eksekusi pencarian pada Array: %.6f ms\n", (t2 - t1) / 1e6);
        System.out.printf("Waktu eksekusi pencarian pada ArrayList: %.6f ms\n", (t4 - t3) / 1e6);

        // Uji dengan data besar
        int bigSize = 1000;
        int[] bigArr = new int[bigSize];
        ArrayList<Integer> bigList = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < bigSize; i++) {
            bigArr[i] = rnd.nextInt(10000);
            bigList.add(bigArr[i]);
        }
        int testVal = bigArr[bigSize / 2];

        long arrStart = System.nanoTime();
        ArrayOperations.linearSearch(bigArr, bigSize, testVal);
        long arrEnd = System.nanoTime();

        long listStart = System.nanoTime();
        ArrayListOperations.search(bigList, testVal);
        long listEnd = System.nanoTime();

        System.out.println("\nPerbandingan Waktu Eksekusi pada Data 1000 Elemen:");
        System.out.printf("Array Linear Search: %.6f ms\n", (arrEnd - arrStart) / 1e6);
        System.out.printf("ArrayList Search:    %.6f ms\n", (listEnd - listStart) / 1e6);

        // Extra: Binary Search (jika ingin)
        // Array harus urut
        Arrays.sort(arr, 0, arrLength);
        int binaryIdx = ArrayOperations.binarySearch(arr, arrLength, 25);
        System.out.println("\nBinary Search 25 dalam Array (terurut): " + (binaryIdx >= 0 ? "Ditemukan di indeks " + binaryIdx : "Tidak ditemukan"));
    }
}
