const managerurl = `https://tasky-app-production.up.railway.app/tasky/manager/7`;
managerData(managerurl);

async function managerData(managerurl) {
    fetch(managerurl)
      .then((res) => res.json())
      .then((response) => {
        console.log(response);
      });
  
}
