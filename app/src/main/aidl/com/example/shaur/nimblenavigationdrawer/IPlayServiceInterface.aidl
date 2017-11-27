package com.example.shaur.nimblenavigationdrawer;

interface IPlayServiceInterface {

    void setPlayingDir(String fileDir);

    oneway void playFile(String fileName);

    oneway void start();

    oneway void pause();

    oneway void resume();

}
