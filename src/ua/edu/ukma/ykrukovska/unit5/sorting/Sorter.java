package ua.edu.ukma.ykrukovska.unit5.sorting;

public class Sorter {

    enum SortingType {
        MERGE,
        BUBBLE,
        INSERTION,
        SELECTION,
        SHELL,
        QUICK;
    }

    public Wrapper[] sort(Wrapper[] data, SortingType type) {
        switch (type) {

            case MERGE:
                return sortMerge(data, data.length);
            case BUBBLE:
                return sortBubble(data);
            case INSERTION:
                return sortInsertion(data);
            case SELECTION:
                return sortSelection(data);
            case SHELL:
                return sortShell(data);
            case QUICK:
                return sortQuick(data, 0, data.length - 1);
            default:
                throw new RuntimeException("Not implemented yet, luv");
        }
    }

    private static Wrapper[] sortBubble(Wrapper data[]) {
        int i, j;
        Wrapper temp;
        int n = data.length;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (data[j].getValue() > data[j + 1].getValue()) {
                    temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
        return data;
    }

    private static Wrapper[] sortSelection(Wrapper data[]) {
        {
            int n = data.length;

            for (int i = 0; i < n - 1; i++) {
                int min_idx = i;
                for (int j = i + 1; j < n; j++)
                    if (data[j].getValue() < data[min_idx].getValue())
                        min_idx = j;

                Wrapper temp = data[min_idx];
                data[min_idx] = data[i];
                data[i] = temp;
            }
            return data;
        }
    }

    private static Wrapper[] sortInsertion(Wrapper[] data) {
        int n = data.length;
        for (int i = 1; i < n; ++i) {
            Wrapper key = data[i];
            int j = i - 1;

            while (j >= 0 && data[j].getValue() > key.getValue()) {
                data[j + 1] = data[j];
                j = j - 1;
            }
            data[j + 1] = key;
        }
        return data;
    }

    private static Wrapper[] sortShell(Wrapper[] data) {
        int n = data.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i += 1) {
                Wrapper temp = data[i];
                int j;
                for (j = i; j >= gap && data[j - gap].getValue() > temp.getValue(); j -= gap)
                    data[j] = data[j - gap];

                data[j] = temp;
            }
        }
        return data;
    }

    private static Wrapper[] sortMerge(Wrapper[] data, int n) {
        if (n < 2) {
            return data;
        }
        int mid = n / 2;
        Wrapper[] l = new Wrapper[mid];
        Wrapper[] r = new Wrapper[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = data[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = data[i];
        }
        sortMerge(l, mid);
        sortMerge(r, n - mid);

        merge(data, l, r, mid, n - mid);

        return data;
    }

    private static void merge(Wrapper[] data, Wrapper[] l, Wrapper[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i].getValue() <= r[j].getValue()) {
                data[k++] = l[i++];
            } else {
                data[k++] = r[j++];
            }
        }
        while (i < left) {
            data[k++] = l[i++];
        }
        while (j < right) {
            data[k++] = r[j++];
        }
    }


    private Wrapper[] sortQuick(Wrapper[] data, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(data, begin, end);

            sortQuick(data, begin, partitionIndex - 1);
            sortQuick(data, partitionIndex + 1, end);
        }

        return data;
    }

    private int partition(Wrapper[] data, int begin, int end) {
        Wrapper pivot = data[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (data[j].getValue() <= pivot.getValue()) {
                i++;

                Wrapper swapTemp = data[i];
                data[i] = data[j];
                data[j] = swapTemp;
            }
        }

        Wrapper swapTemp = data[i + 1];
        data[i + 1] = data[end];
        data[end] = swapTemp;

        return i + 1;
    }

}



