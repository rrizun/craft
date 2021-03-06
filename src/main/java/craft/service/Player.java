package craft.service;

import com.google.gson.annotations.SerializedName;

// playerID,birthYear,
// birthMonth,birthDay,birthCountry,birthState,birthCity,
// deathYear,deathMonth,deathDay,deathCountry,deathState,deathCity,
// nameFirst,nameLast,nameGiven,weight,height,bats,throws,debut,finalGame,retroID,bbrefID

// {
//   "playerID": "zychto01",
//   "birthYear": "1990",
//   "birthMonth": "8",
//   "birthDay": "7",
//   "birthCountry": "USA",
//   "birthState": "IL",
//   "birthCity": "Monee",
//   "deathYear": "",
//   "deathMonth": "",
//   "deathDay": "",
//   "deathCountry": "",
//   "deathState": "",
//   "deathCity": "",
//   "nameFirst": "Tony",
//   "nameLast": "Zych",
//   "nameGiven": "Anthony Aaron",
//   "weight": "190",
//   "height": "75",
//   "bats": "R",
//   "throws": "R",
//   "debut": "2015-09-04",
//   "finalGame": "2017-08-19",
//   "retroID": "zycht001",
//   "bbrefID": "zychto01"
// }
public class Player {
    public String playerID;//": "zychto01",
    public Integer birthYear;//": "1990",
    public Integer birthMonth;//": "8",
    public Integer birthDay;//": "7",
    public String birthCountry;//": "USA",
    public String birthState;//": "IL",
    public String birthCity;//": "Monee",
    public String deathYear;//": "",
    public String deathMonth;//": "",
    public String deathDay;//": "",
    public String deathCountry;//": "",
    public String deathState;//": "",
    public String deathCity;//": "",
    public String nameFirst;//": "Tony",
    public String nameLast;//": "Zych",
    public String nameGiven;//": "Anthony Aaron",
    public String weight;//": "190",
    public String height;//": "75",
    public String bats;//": "R",

    @SerializedName("throws")
    public String _throws;//": "R",
    
    public String debut;//": "2015-09-04",
    public String finalGame;//": "2017-08-19",
    public String retroID;//": "zycht001",
    public String bbrefID;//": "zychto01"
  }
  