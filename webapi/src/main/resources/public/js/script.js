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
  document.getElementById('game').innerHTML = httpGet('http://localhost:8080/map')
};

const post = (path, params) => {
  const method = "post"; // Set method to post by default if not specified.

  const form = document.createElement("form");
  form.setAttribute("method", method);
  form.setAttribute("action", path);

  for (const key in params) {
    if (params.hasOwnProperty(key)) {
      const hiddenField = document.createElement("input");
      hiddenField.setAttribute("type", "hidden");
      hiddenField.setAttribute("name", key);
      hiddenField.setAttribute("value", params[key]);
      form.appendChild(hiddenField);
    }
  }
  document.body.appendChild(form);
  form.submit();
};

// register key listener
window.onkeyup = (e) => {
  console.log('action', e.code);
  const data = new FormData();
  data.append('action', e.code);
  httpPost('/action', data);
};
