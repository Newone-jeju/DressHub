import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Filler {
    private HttpServletRequest req;

    public Filler(HttpServletRequest req)
    {
        this.req = req;
    }

//    public static Filler getInstance(HttpServletRequest req)
//    {
//        return new Filler(req);
//    }

    private boolean isEmpty(String str) {
        boolean isEmpty = false;

        if (str.equals("guide") || str == null) { isEmpty = true; }

        return isEmpty;
    }

    private String yearToAge(String year)
    {
        Calendar calendar = new GregorianCalendar(Locale.KOREA);

        int birthYear = Integer.parseInt(year);
        int currentYear = calendar.get(Calendar.YEAR);
        int age = currentYear - birthYear + 1;
        String result = null;

        if (age >= 0 && age < 20) { result = "10대"; }
        else if (age >= 20 && age < 30) { result = "20대"; }
        else if (age >= 30 && age < 40) { result = "30대"; }
        else if (age >= 40 && age < 50) { result = "40대"; }
        else if (age >= 50 && age < 60) { result = "50대"; }
        else if (age >= 60 && age < 70) { result = "60대"; }
        else if (age >= 70) { result = "70대"; }

        return result;
    }

    public String fillNumOfCompanion()
    {
        String numOfCompanion = req.getParameter("numOfCompanion");

        if (isEmpty(numOfCompanion))
        {
            // TODO: 큐텔에서 파라미터를 제공해 주는 대로 그에 맞춰 바꿔야 함
//            String age = req.getParameter("연령");
//            String job = req.getParameter("직업");
            String age = yearToAge("1994");
            String job = "1";
            if (age.equals("30대") && job.equals("12")) { numOfCompanion = "3"; }
            else if (age.equals("40대")) { numOfCompanion = "3"; }
            else { numOfCompanion = "2"; }
        }

        return numOfCompanion;
    }

    public String fillTypeOfCompanion()
    {
        String typeOfCompanion = req.getParameter("companion");

        if (isEmpty(typeOfCompanion))
        {
            // TODO: 큐텔에서 파라미터를 제공해 주는 대로 그에 맞춰 바꿔야 함
//            String age = req.getParameter("연령");
//            String education = req.getParameter("학력");
////            String job = req.getParameter("직업");
            String education = "1";
            String age = yearToAge("1994");
            String job = "1";

            if (age.equals("10대") && education.equals("2")) { typeOfCompanion = "5"; }
            else if (age.equals("20대")) {
                if (job.equals("11") || job.equals("5") || job.equals("8")
                        || job.equals("13") || job.equals("4")) {
                    typeOfCompanion = "5";
                } else { typeOfCompanion = "2"; }
            } else { typeOfCompanion = "3"; }
        }

        return typeOfCompanion;
    }

    public String fillHowGetInfo()
    {
        String howGetInfo = req.getParameter("infoGet");

        if (isEmpty(howGetInfo))
        {
            // TODO: 큐텔에서 파라미터를 제공해 주는 대로 그에 맞춰 바꿔야 함
//            String age = req.getParameter("연령");
            String age = yearToAge("1994");

            if (age.equals("60대") || age.equals("70대")) { howGetInfo = "2"; }
            else { howGetInfo = "3"; }
        }

        return howGetInfo;
    }

    public String fillStayDuration()
    {
        String stayDuration = req.getParameter("stayDuration");

        if (isEmpty(stayDuration))
        {
            int currentMonth = Calendar.MONTH;

            if(currentMonth == 7 || currentMonth == 8) { stayDuration = "4"; }
            else { stayDuration = "3"; }
        }

        return stayDuration;
    }

    public String fillVisitTime()
    {
        String visitTime = req.getParameter("visitTime");

        if (isEmpty(visitTime))
        {
            // TODO: 큐텔에서 파라미터를 제공해 주는 대로 그에 맞춰 바꿔야 함
//            String age = req.getParameter("연령");
//            String education = req.getParameter("학력");
            String age = yearToAge("1994");
            String education = "1";
            int currentMonth = Calendar.MONTH;

            if (age.equals("30대")) {
                if (currentMonth == 1 || currentMonth == 2) { visitTime = "1"; }
            } else if (education.equals("2") || education.equals("1")) { visitTime = "1"; }
            else { visitTime = "2"; }
        }

        return visitTime;
    }

//    public String fillMinorPresence()
//    {
//        // TODO : 파라미터 이름 확인 및 수정 필요
//        String minorPresence = req.getParameter("만15세미만동반유무");
//
//        if (isEmpty(minorPresence))
//        {
//            String typeOfCompanion = fillTypeOfCompanion();
////            String age = req.getParameter("연령");
//            String age = yearToAge("1994");
//
//            if (typeOfCompanion.equals("3")) {
//                if (age.equals("30대") || age.equals("40대") || age.equals("70대")) { minorPresence = "1";}
//            } else { minorPresence = "0"; }
//        }
//
//        return minorPresence;
//    }

    public String fillTransportation()
    {
        String transportaion = req.getParameter("transportation");

        if (isEmpty(transportaion)) {
            String numOfCompanion = fillNumOfCompanion();

            if (numOfCompanion.equals("1")) { transportaion = "3"; }
            else if (numOfCompanion.equals("10")) { transportaion = "4"; }
            else { numOfCompanion = "1"; }
        }

        return transportaion;
    }

    public String fillReason1()
    {
        String reason1 = req.getParameter("considerReason1");

        if (isEmpty(reason1))
        {
            String howGetInfo = fillHowGetInfo();
            String transportaion = fillTransportation();

            if (howGetInfo.equals("8") || howGetInfo.equals("5")
                    || howGetInfo.equals("6")) {
                if (transportaion.equals("6") || transportaion.equals("2")
                        || transportaion.equals("5"))
                {
                    reason1 = "2";
                }
            } else { reason1 = "1"; }
        }

        return reason1;
    }

    public String fillReason2()
    {
        String reason2 = req.getParameter("considerReason2");

        if (isEmpty(reason2))
        {
            String reason1 = fillReason1();

            if (reason1.equals("1") || reason1.equals("8")) { reason2 = "2"; }
            else if (reason1.equals("7") || reason1.equals("6") || reason1.equals("9")
                    || reason1.equals("10")) { reason2 = "8"; }
            else { reason2 = "4"; }
        }

        return reason2;
    }

    public String fillMainDest()
    {
        String mainDestination = req.getParameter("primeReason");

        if (isEmpty(mainDestination))
        {
            mainDestination = fillReason1();
        }

        return mainDestination;
    }

    public String fillTripType()
    {
        String typeOftrip = req.getParameter("tripType");

        if (isEmpty(typeOftrip)) {
            String transportation = fillTransportation();
            String numOfCompanion = fillNumOfCompanion();

            if (transportation.equals("4")) { typeOftrip = "2"; }
            else if (transportation.equals("2") || transportation.equals("5")) {
                if (numOfCompanion.equals("5") || numOfCompanion.equals("10")) { typeOftrip = "2"; }
            } else { typeOftrip = "1"; }
        }

        return typeOftrip;
    }

    public String fillAccomodation()
    {
        String accomodation = req.getParameter("accomodation");

        if (isEmpty(accomodation))
        {
            // TODO: 큐텔에서 파라미터를 제공해 주는 대로 그에 맞춰 바꿔야 함
            String transportaion = fillTransportation();
            String typeOfCompanion = fillTypeOfCompanion();
            String stayDuration = fillStayDuration();
//            String age = req.getParameter("연령");
            String age = yearToAge("1994");


            if (typeOfCompanion.equals("5") || typeOfCompanion.equals("1")) {
                if (age.equals("20대") && stayDuration.equals("4")) { accomodation = "3"; }
                else if (age.equals("10대") || age.equals("30대")) {
                    if (transportaion.equals("3") || transportaion.equals("6")
                            || transportaion.equals("5")) {
                        accomodation = "3";
                    }
                }
            } else { accomodation = "1"; }
        }

        return accomodation;
    }
}
