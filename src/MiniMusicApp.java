import javax.sound.midi.*;

public class MiniMusicApp {
    public static void main(String[] args) {
        MiniMusicApp miniApp = new MiniMusicApp();
        if (args.length < 2) {
            System.out.println("Не забудьте ввести инструмент и ноту!");
        } else {
            int instrument = Integer.parseInt(args[0]);
            int note = Integer.parseInt(args[1]);
            miniApp.play(instrument, note);
        }
        miniApp = null;
        // sss
        // aaa
    }

    public void play(int instrument, int note) {
        try {
            Sequencer player = MidiSystem.getSequencer();
            player.open();
            player.setLoopCount(0);

            Sequence seq = new Sequence(Sequence.PPQ, 4);

            Track track = seq.createTrack();

            ShortMessage first = new ShortMessage();
            first.setMessage(192, 1, instrument, 0);
            MidiEvent changeInstrument = new MidiEvent(first, 1);
            track.add(changeInstrument);

            ShortMessage a = new ShortMessage();
            a.setMessage(144, 1, note, 100);
            MidiEvent noteOn = new MidiEvent(a, 3);
            track.add(noteOn);

            ShortMessage b = new ShortMessage();
            b.setMessage(128, 1, note, 100);
            MidiEvent noteOff = new MidiEvent(b, 14);
            track.add(noteOff);

            player.setSequence(seq);

            player.start();
            while (player.isRunning()) {
//                Thread.sleep(5);
//                if (!player.isRunning()) {
//                    player.stop();
//                    player.close();
//                }
            }
            player.close();
//            System.exit(0);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
