package no.sanderolin;

import no.sanderolin.day01.NotQuiteLisp01;
import no.sanderolin.day01.NotQuiteLisp02;
import no.sanderolin.day02.IWasToldThereWouldBeNoMath01;
import no.sanderolin.day02.IWasToldThereWouldBeNoMath02;
import no.sanderolin.day03.PerfectlySphericalHousesInAVacuum01;
import no.sanderolin.day03.PerfectlySphericalHousesInAVacuum02;
import no.sanderolin.day04.TheIdealStockingStuffer01;
import no.sanderolin.day04.TheIdealStockingStuffer02;
import no.sanderolin.day05.DoesntHeHaveInternElvesForThis01;
import no.sanderolin.day05.DoesntHeHaveInternElvesForThis02;
import no.sanderolin.day06.ProbablyAFireHazard01;
import no.sanderolin.day06.ProbablyAFireHazard02;
import no.sanderolin.day07.SomeAssemblyRequired01;
import no.sanderolin.day07.SomeAssemblyRequired02;
import no.sanderolin.day08.Matchsticks01;
import no.sanderolin.day08.Matchsticks02;
import no.sanderolin.day09.AllInASingleNight01;
import no.sanderolin.day09.AllInASingleNight02;
import no.sanderolin.day10.ElvesLookElvesSay01;
import no.sanderolin.day10.ElvesLookElvesSay02;
import no.sanderolin.day11.CorporatePolicy01;
import no.sanderolin.day11.CorporatePolicy02;
import no.sanderolin.day12.JSAbacusFrameworkIO01;
import no.sanderolin.day12.JSAbacusFrameworkIO02;
import no.sanderolin.day13.KnightsOfTheDinnerTable01;
import no.sanderolin.day13.KnightsOfTheDinnerTable02;
import no.sanderolin.day14.ReindeerOlympics01;
import no.sanderolin.day14.ReindeerOlympics02;
import no.sanderolin.day15.ScienceForHungryPeople01;
import no.sanderolin.day15.ScienceForHungryPeople02;
import no.sanderolin.day16.AuntSue01;
import no.sanderolin.day16.AuntSue02;
import no.sanderolin.day17.NoSuchThingAsTooMuch01;
import no.sanderolin.day17.NoSuchThingAsTooMuch02;
import no.sanderolin.day18.LikeAGIFForYourYard01;
import no.sanderolin.day18.LikeAGIFForYourYard02;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean continueProgram = true;

        System.out.println("Advent of Code 2015!");

        while (continueProgram) {
            System.out.print("Enter the day you want to see the solution for (e.g., 1, 2, 3): ");
            int day = scanner.nextInt();
            System.out.print("Enter the part you want to see the solution for (1 or 2): ");
            int part = scanner.nextInt();
            try {
                if (day == 1 && part == 1) {
                    NotQuiteLisp01.solve();
                } else if (day == 1 && part == 2) {
                    NotQuiteLisp02.solve();
                } else if (day == 2 && part == 1) {
                    IWasToldThereWouldBeNoMath01.solve();
                } else if (day == 2 && part == 2) {
                    IWasToldThereWouldBeNoMath02.solve();
                } else if (day == 3 && part == 1) {
                    PerfectlySphericalHousesInAVacuum01.solve();
                } else if (day == 3 && part == 2) {
                    PerfectlySphericalHousesInAVacuum02.solve();
                } else if (day == 4 && part == 1) {
                    TheIdealStockingStuffer01.solve();
                } else if (day == 4 && part == 2) {
                    TheIdealStockingStuffer02.solve();
                } else if (day == 5 && part == 1) {
                    DoesntHeHaveInternElvesForThis01.solve();
                } else if (day == 5 && part == 2) {
                    DoesntHeHaveInternElvesForThis02.solve();
                } else if (day == 6 && part == 1) {
                    ProbablyAFireHazard01.solve();
                } else if (day == 6 && part == 2) {
                    ProbablyAFireHazard02.solve();
                } else if (day == 7 && part == 1) {
                    SomeAssemblyRequired01.solve();
                } else if (day == 7 && part == 2) {
                    SomeAssemblyRequired02.solve();
                } else if (day == 8 && part == 1) {
                    Matchsticks01.solve();
                } else if (day == 8 && part == 2) {
                    Matchsticks02.solve();
                } else if (day == 9 && part == 1) {
                    AllInASingleNight01.solve();
                } else if (day == 9 && part == 2) {
                    AllInASingleNight02.solve();
                } else if (day == 10 && part == 1) {
                    ElvesLookElvesSay01.solve();
                } else if (day == 10 && part == 2) {
                    ElvesLookElvesSay02.solve();
                } else if (day == 11 && part == 1) {
                    CorporatePolicy01.solve();
                } else if (day == 11 && part == 2) {
                    CorporatePolicy02.solve();
                } else if (day == 12 && part == 1) {
                    JSAbacusFrameworkIO01.solve();
                } else if (day == 12 && part == 2) {
                    JSAbacusFrameworkIO02.solve();
                } else if (day == 13 && part == 1) {
                    KnightsOfTheDinnerTable01.solve();
                } else if (day == 13 && part == 2) {
                    KnightsOfTheDinnerTable02.solve();
                } else if (day == 14 && part == 1) {
                    ReindeerOlympics01.solve();
                } else if (day == 14 && part == 2) {
                    ReindeerOlympics02.solve();
                } else if (day == 15 && part == 1) {
                    ScienceForHungryPeople01.solve();
                } else if (day == 15 && part == 2) {
                    ScienceForHungryPeople02.solve();
                } else if (day == 16 && part == 1) {
                    AuntSue01.solve();
                } else if (day == 16 && part == 2) {
                    AuntSue02.solve();
                } else if (day == 17 && part == 1) {
                    NoSuchThingAsTooMuch01.solve();
                } else if (day == 17 && part == 2) {
                    NoSuchThingAsTooMuch02.solve();
                } else if (day == 18 && part == 1) {
                    LikeAGIFForYourYard01.solve();
                } else if (day == 18 && part == 2) {
                    LikeAGIFForYourYard02.solve();
                } else {
                    System.out.println("No solution available for Day " + day + ", Part " + part);
                }
            } catch (Exception e) {
                System.out.println("An error occurred while trying to solve the problem: " + e.getMessage());
            }
            System.out.print("Do you want to see another solution? (y/n): ");
            scanner.nextLine();
            String response = scanner.nextLine().trim().toLowerCase();

            if (!response.equals("y")) {
                continueProgram = false;
                System.out.println("Goodbye!");
            }
        }
        scanner.close();
    }
}