/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playlist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

/**
 *
 * @author esteb
 */
public class Playlist {
    
    private static List<Album> albums = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     Album album = new Album("The Wall", "PinkFloyd");
        album.addSong("In the flesh?", 4.6);
        album.addSong("Another Brick in the Wall (II)", 4.22);
        album.addSong("The thin ice", 4.3);
        album.addSong("Mother", 5.6);
        album.addSong("Goodbye blue sky", 3.21);
        albums.add(album);

        album = new Album("Enter Sandman", "Metallica");
        album.addSong("Enter sandman", 5.44);
        album.addSong("Wherever i may roam", 3.25);
        album.addSong("Sad but true", 3.45);
        album.addSong("The unforgiven", 3.33);
        album.addSong("Nothing else matters", 4.51);
        album.addSong("Thorugh the never", 3.45);
        albums.add(album);

        List<Song> playList = new LinkedList<>();
        albums.get(0).addToPlayList("The unforgiven", (LinkedList<Song>) playList);
        albums.get(0).addToPlayList("Mother", (LinkedList<Song>) playList);
        albums.get(0).addToPlayList("Enter sandman", (LinkedList<Song>) playList);  
        albums.get(0).addToPlayList(9, (LinkedList<Song>) playList);
        albums.get(1).addToPlayList(8, (LinkedList<Song>) playList);
        albums.get(1).addToPlayList(3, (LinkedList<Song>) playList);
        albums.get(1).addToPlayList(2, (LinkedList<Song>) playList);
        albums.get(1).addToPlayList(24, (LinkedList<Song>) playList);  

        play(playList);
    }
    
    private static void play(List<Song> playList) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;
        ListIterator<Song> listIterator = playList.listIterator();
        if(playList.isEmpty()) {
            System.out.println("No songs in playlist");
            return;
        } else {
            System.out.println("Now playing " + listIterator.next().toString());
            printMenu();
        }

        while(!quit) {
            int action = scanner.nextInt();
            scanner.nextLine();

            switch(action) {
                case 0:
                    System.out.println("Playlist complete.");
                    quit = true;
                    break;
                case 1:
                    if(!forward) {
                        if(listIterator.hasNext()) {
                            listIterator.next();
                        }
                        forward = true;
                    }
                    if(listIterator.hasNext()) {
                        System.out.println("Now playing " + listIterator.next().toString());
                    } else {
                        System.out.println("We have reached the end of the playlist");
                        forward = false;
                    }
                    break;

                case 2:
                    if(forward) {
                        if(listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        forward = false;
                    }
                    if(listIterator.hasPrevious()) {
                        System.out.println("Now playing " + listIterator.previous().toString());
                    } else {
                        System.out.println("We are at the start of the playlist");
                        forward = true;
                    }
                    break;
                case 3:
                    if(forward) {
                        if(listIterator.hasPrevious()) {
                            System.out.println("Now replaying " + listIterator.previous().toString());
                            forward = false;
                        } else {
                            System.out.println("We are at the start of the list");
                        }
                    } else {
                        if(listIterator.hasNext()) {
                            System.out.println("Now replaying " + listIterator.next().toString());
                            forward = true;
                        } else {
                            System.out.println("We have reached the end of the list");
                        }
                    }
                    break;
                case 4:
                    printList(playList);
                    break;
                case 5:
                    printMenu();
                    break;

                case 6:
                    if(playList.size() >0) {
                        listIterator.remove();
                        if(listIterator.hasNext()) {
                            System.out.println("Now playing " + listIterator.next());
                        } else if(listIterator.hasPrevious()) {
                            System.out.println("Now playing " + listIterator.previous());
                        }
                    }
                    break;

            }
        }
    }

    private static void printMenu() {
        System.out.println("Available actions:\npress");
        System.out.println("0 - to quit\n" +
                "1 - to play next song\n" +
                "2 - to play previous song\n" +
                "3 - to replay the current song\n" +
                "4 - list songs in the playlist\n" +
                "5 - print available actions.\n" +
                "6 - delete current song from playlist");

    }


    private static void printList(List<Song> playList) {
        Iterator<Song> iterator = playList.iterator();
        System.out.println("================================");
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("================================");
    }
}