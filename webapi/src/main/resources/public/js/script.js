const SYNCHRONOUS = false;

const httpGet = (theUrl) => {
  const xmlHttp = new XMLHttpRequest();
  xmlHttp.open("GET", theUrl, SYNCHRONOUS);
  xmlHttp.send(null);
  return xmlHttp.responseText;
};

const httpPost = (theUrl, data) => {
  const xmlHttp = new XMLHttpRequest();
  xmlHttp.open("POST", theUrl, SYNCHRONOUS);
  xmlHttp.send(data);
  return xmlHttp.responseText;
};

const start = () => {
  updateView(httpGet('http://localhost:8080/map'));
};

// register key listener
window.onkeyup = (e) => {
  console.log('action', e.code);
  const data = new FormData();
  data.append('action', e.code);
  updateView(httpPost('/action', data));
};

const updateView = (newContent) => {
  document.getElementById('game').innerHTML = newContent;
};

