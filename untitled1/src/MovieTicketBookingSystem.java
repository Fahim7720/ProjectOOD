import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieTicketBookingSystem extends JFrame implements ActionListener {
    private JButton[] seats;
    private boolean[] seatAvailability;
    private String movieName;

    public MovieTicketBookingSystem(String movieName) {
        this.movieName = movieName;
        setTitle("Movie Ticket Booking System - " + movieName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 5));

        seats = new JButton[25];
        seatAvailability = new boolean[25];

        for (int i = 0; i < 25; i++) {
            seats[i] = new JButton("Seat " + (i + 1));
            seats[i].addActionListener(this);
            add(seats[i]);
            seatAvailability[i] = true;
        }

        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton selectedSeat = (JButton) e.getSource();
        int seatNumber = -1;

        for (int i = 0; i < 25; i++) {
            if (seats[i] == selectedSeat) {
                seatNumber = i;
                break;
            }
        }

        if (seatNumber >= 0) {
            if (seatAvailability[seatNumber]) {
                seatAvailability[seatNumber] = false;
                selectedSeat.setBackground(Color.RED);
                selectedSeat.setEnabled(false);
                int paymentAmount = 10; // Assuming the ticket price is $10
                boolean paymentSuccessful = processPayment(paymentAmount);
                if (paymentSuccessful) {
                    JOptionPane.showMessageDialog(this, "Seat " + (seatNumber + 1) + " booked for " + movieName + "!\nPayment successful!");
                } else {
                    JOptionPane.showMessageDialog(this, "Payment failed! Seat " + (seatNumber + 1) + " is not booked.");
                    seatAvailability[seatNumber] = true;
                    selectedSeat.setBackground(null);
                    selectedSeat.setEnabled(true);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seat " + (seatNumber + 1) + " is already booked!");
            }
        }
    }

    private boolean processPayment(int amount) {
        String cardNumber = JOptionPane.showInputDialog("Enter your bkash/Nagad/Rocket/Bank Acc. number:");
        String cvv = JOptionPane.showInputDialog("Enter your OTP:");
        return !cardNumber.isEmpty();
    }

    public static void main(String[] args) {
        String movieName = JOptionPane.showInputDialog("Enter the movie name:");
        new MovieTicketBookingSystem(movieName);
    }
}
