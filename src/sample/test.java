//public static void write() throws IOException {
//        String line;
//        FileWriter fw = new FileWriter("usersInfo.txt");
//        BufferedWriter bw = new BufferedWriter(fw);
//        for (Customer s1 : customer) {
//            line = s1.getUserName() + "     " + s1.getPassWord() + "     " + s1.getFName()
//                    + "     " + s1.getLName() + "     " + s1.getBirthDAy() + "     " + s1.getPhoneNumber()
//                    + "     " + s1.getMobileNumber() + "     " + s1.getMeliCode() + "     " + s1.getAddress() + "     " + s1.getPostalCode() + "     " + s1.getEtelateSabadekharid() + "     " + s1.getMablagheSabadekharid();
//
//            bw.write(line);
//            bw.newLine();
//        }
//        bw.flush();
//        bw.close();
//        System.out.println("All files Of Customer have been Writen!!!");
//    }
//
//    public static void read() throws Exception {
//        FileReader fr = new FileReader("usersInfo.txt");
//        BufferedReader br = new BufferedReader(fr);
//        String line, str[];
//
//        while ((line = br.readLine()) != null) {
//
//            str = line.split("     ");
//            Customer s1 = new Customer(str[0], str[1], str[2], str[3], str[4], str[5], str[6], str[7], str[8], str[9], str[10], str[11]);
//            customer.add(s1);
//        }
//
//        br.close();
//        System.out.println("All files Of Customer have been read!!!");
//    }
