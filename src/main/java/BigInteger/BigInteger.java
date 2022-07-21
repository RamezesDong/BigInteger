package BigInteger;

public class BigInteger {
    // array to store the integer. Every element can represent -127 to 127
    char[] data;
    int capacity;
    // size indicates the number of bytes used
    int size;

    /**
     * Construct a BigInteger with capacity and initial value.
     * Maybe need to reset the BigInteger.
     * @param num initial value
     * @param cap initial capacity
     */
    public BigInteger(int cap, int num) {
        assert(cap > 0);
        this.capacity = cap;
        this.data = new char[this.capacity];

        for (int i = 0; i < this.capacity; i++) {
            data[i] = convertToChar(num % 128);
            num = num / 128;
            if (num == 0) {
                this.size = i + 1;
                break;
            } else if (i == this.capacity - 1) {
                this.resize(this.capacity * 2);
            }
        }
    }

    /**
     * help function to convert int to char, by using bitwise operations
     * for example: 127(0000 0..0 0111 1111) -> '0111 1111';  -127(1111 1..1 1000 0001) -> '1000 0001'
     * @param val int value < 128
     * @return character type value
     */
    private static char convertToChar(int val) {
        if (val >= 0) {
            return (char) val;
        } else {
            val = val &((1 << 7) - 1);
            val = val | (1 << 7);
            return (char) val;
        }
    }

    /**
     * help function to restore to original int value
     * @param c character
     * @return int value
     */
    private static int charToInt(char c) {
        if (c < 128) {
            return (int) c;
        } else if (c > 128) {
            return ((int) c) - (1 << 8);
        } else {
            assert(false);
            return 128;
        }
    }

    /**
     * add two BigIntegers
     * @return the result as a BigIntegers
     */
    public static BigInteger add(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    /**
     * add b parameter to this BigInteger
     * @param b a BigInteger instance
     * @return result BigInteger
     */
    public BigInteger add(BigInteger b) {
        int maxSize = Math.max(this.size, b.size);
        // use maxSize + 1 capacity can contain the result
        BigInteger res = new BigInteger(maxSize + 1, 0);
        int index = 0;
        int remainder = 0;
        // ugly code implementation
        while (index < this.size && index < b.size) {
            int sum = charToInt(this.data[index]) + charToInt(b.data[index]) + remainder;
            res.data[index] = convertToChar(sum % 128);
            remainder = sum / 128;
            index++;
        }
        while (index < this.size) {
            int sum = charToInt(this.data[index]) + remainder;
            res.data[index] = convertToChar(sum % 128);
            remainder = sum / 128;
            index++;
        }
        while (index < b.size) {
            int sum = charToInt(b.data[index]) + remainder;
            res.data[index] = convertToChar(sum % 128);
            remainder = sum / 128;
            index++;
        }
        res.size = index;
        if (remainder != 0) {
            res.data[index] = convertToChar(remainder);
            res.size = index + 1;
        }
        return res;
    }

    /**
     * resize the data with new capacity which is usually twice of original capacity
     * @param newCapacity the size of new capacity
     */
    private void resize(int newCapacity) {
        char[] newData = new char[newCapacity];
        System.arraycopy(this.data, 0, newData, 0, this.capacity);
        this.data = newData;
        this.capacity = newCapacity;
    }

    /**
     * method to test the class, maybe overflow
     * @return int value of the BigInteger
     */
    public int toInteger() {
        int res = 0;
        int point = 1;
        for (int i = 0; i < this.size; i++) {
            res = res + charToInt(data[i]) * point;
            point *= 128;
        }
        return res;
    }

    public static void main(String[] args) {
        BigInteger a = new BigInteger(10, -2500);
        BigInteger b = new BigInteger(10, -5200);
        System.out.println("a is " + a.toInteger());
        System.out.println("b is " + b.toInteger());
        System.out.println(a.add(b).toInteger());
    }
}
