
package lab04;

public class Calculator {

    public int complexAdd(int a, int b) {
        if (a < 2) {
            return (a + b) * -1;
        } else {
            return a + b;
        }
    }
}
