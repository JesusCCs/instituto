const express = require('express');
const API = require('../api/index');
const router = express.Router();

router.get('/', function(req, res, next) {
  API.getAllAnimes().then(data => {
    res.json(data);
  })
});

router.get('/portada', function(req, res, next) {
  API.getLatestEpisodes().then(async data =>  {

    for (const item of data) {
      item.image = "https://www3.animeflv.net" + item.image.slice(9);
      let titleArr = item.title.split("-");
      titleArr.pop();
      item.title = titleArr.join("-");

      const {rate, votes} = await API.getAnimeInfo(item.title);
      item.rate = rate;
      item.votes = votes;
    }

    res.json(data);
  })
});

router.get('/titulo/:titulo', function(req, res, next) {
  API.getAnimeInfo(req.params.titulo).then(data => {
    res.json(data);
  })
});

router.get('/buscar/:busqueda', function(req, res, next) {
  API.searchAnime(req.params.busqueda).then(async data => {

    for (const item of data) {
      const {rate, votes} = await API.getAnimeInfo(item.title);
      item.rate = rate;
      item.votes = votes;
    }

    res.json(data);
  });
});

router.get('/titulo/:titulo/:episodioIndex', function(req, res, next) {
  API.getEpisodeVideos(req.params.episodioIndex, req.params.titulo).then(data => {
    res.json(data);
  })
});

module.exports = router;
