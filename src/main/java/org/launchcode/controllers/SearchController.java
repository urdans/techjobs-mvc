package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // DONE #1 - Create handler to process search request and display results

    @RequestMapping(value = "results")
    public String searchby(Model model, @RequestParam String searchTerm, @RequestParam String searchType) {
        ArrayList<HashMap<String, String>> jobListResult;
//        int resultCount;
        if(searchType.equals("all")){
            jobListResult = JobData.findByValue(searchTerm);
        }else{
            jobListResult = JobData.findByColumnAndValue(searchType, searchTerm);
        }
//        resultCount = jobListResult.size();

        model.addAttribute("columns", ListController.columnChoices);
//        model.addAttribute("resultCount", resultCount);
        model.addAttribute("jobListResult", jobListResult);
        /*
        I would like to use the following static method to make every column header capitalized, like "Position Type"
        instead of "position type". Ho to achieve this? ask the TA.
        org.apache.commons.text.WordUtils.capitalize
        */
        return "search";
    }
}
