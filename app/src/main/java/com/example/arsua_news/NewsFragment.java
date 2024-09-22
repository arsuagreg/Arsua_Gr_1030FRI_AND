package com.example.arsua_news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class NewsFragment extends Fragment {

    TextView newsTitle;
    TextView newsContent;

    // News content
    String[] news = {
            "MANILA, Philippines — Vice President Sara Duterte once again declined to answer questions about her office's budget utilization at a House inquiry on a privilege speech about her office's alleged fund misuse on Wednesday, September 18. \n" +
                    "\n" +
                    "The House of Representatives' Good Governance and Public Accountability Committee conducted a hearing to deliberate on the budget use of the Office of the Vice President (OVP), where they invited Duterte to explain state auditors’ findings on her agency’s spending of confidential funds. \n" +
                    "\n" +
                    "While Duterte appeared at the hearing as a resource person, she refused to take oath promising that any statement she makes during the inquiry is true.",
            "MANILA, Philippines — Twenty individuals were killed due to the combined effects of the enhanced southwest monsoon, locally known as habagat, and tropical cyclones \"Ferdie\" and \"Gener\", the National Disaster Risk Reduction and Management Council (NDRRMC) reported on Wednesday, September 18.\n" +
                    "\n" +
                    "In its 8 a.m. report, the NDRRMC validated 20 deaths and 11 injuries due to the combined effects of habagat and the tropical cyclones.\n" +
                    "\n" +
                    "Fourteen inviduals were also recorded missing. Of which, 12 were recorded in Mimaropa and one each in Regions VI and IX, respectively.\n" +
                    "\n" +
                    "The number of affected individuals residing in 1,178 barangays in 12 regions reached an estimated 597,870.",
            "MANILA, Philippines — A former chief of the Philippine National Police (PNP) may have been bribed to help dismissed Bamban mayor Alice Guo escape, a Philippine Amusement and Gaming Corp. (PAGCOR) official told senators yesterday.\n" +
                    "\n" +
                    "During the continuation of the Senate’s public hearing on Philippine offshore gaming operator-related crimes, Sen. Risa Hontiveros asked PAGCOR senior vice president Raul Villanueva about the possibility that government officials may have helped Guo escape in exchange for money.\n" +
                    "\n" +
                    "“Apart from that general detail, which is P200 million (given) to a high Bureau of Immigration official, are there other people who were allegedly bribed? Are there additional amounts being discussed?” Hontiveros said.",
            "MANILA, Philippines — Police have intensified the manhunt for Harry Roque, who has an arrest order issued by the House of Representatives quad committee, with special tracker teams looking for the former presidential spokesman round the clock.\n" +
                    "\n" +
                    "Philippine National Police (PNP) chief Gen. Rommel Francisco Marbil said yesterday they have mobilized tracker teams led by the Criminal Investigation and Detection Group to locate Roque and bring him before the House’s quad committee.\n" +
                    "\n" +
                    "“We have officially received the request from the House of Representatives and the PNP is fully committed to executing this order while adhering to our core mandate,” Marbil said in a statement.\n" +
                    "\n" +
                    "The House quad committee has a standing order for Roque’s arrest over his failure to submit subpoenaed documents related to his assets. Lawmakers believe the documents could be connected to criminal activities of illegal Philippine offshore gaming operation hubs.",
            "MANILA, Philippines — The tenure of Presidential Task Force on Media Security Executive Director Paul Gutierrez has ended.\n" +
                    "\n" +
                    "This has been confirmed by Gutierrez in a statement to the members of the press on Wednesday, September 18.\n" +
                    "\n" +
                    "Gutierrez thanked President Ferdinand Marcos Jr. and other officials for their “full support” in addressing and bringing justice to the killings of the members of the media. Prior to his statement, Executive Secretary Lucas Bersamin informed Justice Secretary Jesus Crispin Remulla, the co-chair of the task force, regarding the end of Gutierrez’s tenure. \n" +
                    "\n" +
                    "The task force was made in 2016 through Administrative Order No. 1 to protect media workers from violence."
    };
    private int selectedPosition = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment, container, false);

        newsTitle = view.findViewById(R.id.news_title);
        newsContent = view.findViewById(R.id.news_content);

        if (getArguments() != null) {
            selectedPosition = getArguments().getInt("position", 0);
            updateNews(selectedPosition);
        }

        return view;
    }
    public void updateNews(int position) {
        newsTitle.setText("Headline " + (position + 1));
        newsContent.setText(news[position]);
    }
}
