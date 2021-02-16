import java.util.ArrayList;
import java.util.List;

public class Clock {

    List<BaseState> states = new ArrayList<>();
    int stateIndex = 0;
    BaseState currentState;

    private Clock() {
        states.add(new Time());
        states.add(new Alarm());
        states.add(new Timer());
        currentState = states.get(0);
    }

    // Move to next state.
    void A() {
        currentState.A();

        // stateIndex = (stateIndex + 1) % states.size();
        // currentState = states.get(stateIndex);
    }

    // B logic of current state.
    void B() {
        currentState.B();
    }

    // C logic of current state.
    void C() {

        currentState.C();
    }

    // D logic of current state.
    void D() {
        currentState.D();
    }

    // 1. All states have different logic on button B,C,D and the A button is responsible to change between states. Answer: interface
    // 2. All state have different logic on button B,C, same logic on button D and A is responsible to change between states. Answer: abstract class
    // 3. If the time of the clock is not set there is no meaning to alarm state so we need to skip it when we change states. Answer: push logic of A button to the abstract class.

    // interface IState {
    //   void B();
    //   void C();
    //   void D();
    // }

    abstract class BaseState {

        void A() {
            stateIndex++;
        }
        abstract void B();
        abstract void C();
        void D() {
            System.out.println("Same logic to all states like turn on light");
        }
    }

    class Time extends BaseState {

        boolean isTimeSet = false;

        @Override
        public void A() {
            if (!isTimeSet) {
                stateIndex += 2;
            }
            else {
                this.A();
            }
        }

        @Override
        public void B() {
            isTimeSet = true;
            System.out.println("B logic of Time");
        }

        @Override
        public void C() {
            System.out.println("C logic of Time");
        }
    }

    class Alarm extends BaseState {

        @Override
        public void B() {
            System.out.println("B logic of Alarm");
        }

        @Override
        public void C() {
            System.out.println("C logic of Alarm");
        }
    }

    class Timer extends BaseState {

        @Override
        public void B() {
            System.out.println("B logic of Timer");
        }

        @Override
        public void C() {
            System.out.println("C logic of Timer");
        }
    }
}
