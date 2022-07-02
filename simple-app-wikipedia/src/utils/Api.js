export const getBusqueda = async (busqueda, offSet = 0) => {
  const URL = `https://es.wikipedia.org/w/api.php?format=json&action=query&list=search&srprop=snippet&srsearch=${busqueda}&sroffset=${offSet}`;
  try {
    const response = await fetch(URL);
    const {query} = await response.json();
    return {lista: query.search, numMax: query.searchinfo.totalhits};
  } catch (err) {
    return {lista: false};
  }
};

export const getPage = async (pageid) => {
  const URL = `https://es.wikipedia.org/w/api.php?format=json&action=parse&prop=text&pageid=${pageid}`;
  try {
    const response = await fetch(URL);
    const {parse} = await response.json();
    return parse.text['*'];
  } catch (err) {
    return false;
  }
};
