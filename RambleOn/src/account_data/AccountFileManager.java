package account_data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import ramble_on.RambleOn;
import static ramble_on.RambleOnSettings.*;


public class AccountFileManager {

    private RambleOn ro;
    File directory;
    private ArrayList<File> accountFiles;

    public AccountFileManager(RambleOn initRo) {
        ro = initRo;
        accountFiles = new ArrayList();
        directory = new File(ACCOUNTS_PATH);
    }

    public File getDirectory() {
        return directory;
    }

    public void loadAccounts() {
        File[] folder = directory.listFiles();
        for (int i = 0; i < directory.listFiles().length; i++) {
            accountFiles.add(folder[i]);
        }
    }

    public ArrayList<File> getFiles() {
        return accountFiles;
    }

    public Account newFile(String name) {
        Account newAccount = new Account(name);

        try {
            FileOutputStream fileOut =
                    new FileOutputStream(ACCOUNTS_PATH + name);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(newAccount);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }

        return newAccount;
    }
    
    public void saveFile(File name, Account account)
    {
        try {
            FileOutputStream fileOut = new FileOutputStream(name);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(account);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void loadFile(File file) {
        file.listFiles();
    }
}
