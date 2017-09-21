const express = require('express');
const bodyParser = require('body-parser');
const cloudinary = require('cloudinary');
const axios = require('axios');

const port = process.env.PORT || 3000;


let app = express();

app.use(bodyParser.json({limit: '50mb'}));
app.use(bodyParser.urlencoded({limit: '50mb', extended: true}));

cloudinary.config({
    cloud_name: 'da6yjdm3d',
    api_key: '613653817217321',
    api_secret: 'F6-z9RKRSc4nznItFcVx2kxeDsc'
});


axios.defaults.headers.post['Prediction-Key'] = '776c6c0e4f0d4065aa348056241146e0';
axios.defaults.headers.post['Training-key'] = '8bc0a3283f534af1a556c26d53b7f333';
axios.defaults.headers.get['Training-key'] = '8bc0a3283f534af1a556c26d53b7f333';
axios.defaults.headers.post['Prediction-Key'] = '776c6c0e4f0d4065aa348056241146e0';

app.post('/captureImg', (req, res) => {
    let imgStr = req.body.imgString;
    cloudinary.uploader.upload(imgStr, (result) => {
        let getInfo = 'https://southcentralus.api.cognitive.microsoft.com/customvision/v1.0/Training/projects/1b1cddff-1f9e-4bcd-aaa3-9cf2dab35784';
        //let predURL = `https://southcentralus.api.cognitive.microsoft.com/customvision/v1.0/Prediction/1b1cddff-1f9e-4bcd-aaa3-9cf2dab35784/url?iterationId=${response.data.CurrentIterationId}`;
        let predURL = `https://southcentralus.api.cognitive.microsoft.com/customvision/v1.0/Prediction/1b1cddff-1f9e-4bcd-aaa3-9cf2dab35784/url?iterationId=b9a7e9d1-126e-403f-b84d-a2988f428eb5`;
        axios.post(predURL, {
            "Url": result.url
        }).then((resp) => {
            let max_value = resp.data.Predictions[0].Probability;
            let max_tag = resp.data.Predictions[0].Tag;
            for (let i = 1; i < resp.data.Predictions.length; i++) {
                if (resp.data.Predictions[i].Probability > max_value) {
                    max_value = resp.data.Predictions[i].Probability;
                    max_tag = resp.data.Predictions[i].Tag;
                }
            }
            let urlEndPoint = "https://intense-inlet-86834.herokuapp.com/api/search";
            return axios.post(urlEndPoint, {"query" : "yay"}).then((ress) => {
                console.log (ress.data);
                res.send(ress.data);
            })
        }).catch((err) => {
            console.log(err);
            res.send(err);
        })
    });
});

app.listen(port, () => {
    console.log(`Server is open on port ${port}`);
});