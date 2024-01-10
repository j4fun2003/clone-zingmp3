let audioContext;

const firebaseUrl = "https://storage.googleapis.com/zing-mp3-2118c.appspot.com/";

function uploadAudioFromFireBase(audioFile) {
    const formData = new FormData();
    formData.append("file", audioFile);

    // Gửi formData lên server sử dụng AJAX
    $.ajax({
        url: "/api/firebase/upload",
        method: "POST",
        data: formData,
        contentType: false,
        processData: false,
        error: function (error) {
            alertError("Upload To firebase Failed!", error);
        }
    });
}




function getAudioFromFirebase(fileName) {
    return fetch(`/api/firebase/download/${fileName}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch audio');
            }
            return response.arrayBuffer();
        })
        .then(arrayBuffer => {
            if (!audioContext) {
                audioContext = new (window.AudioContext || window.webkitAudioContext)();
            }
            return new Promise((resolve, reject) => {
                audioContext.decodeAudioData(arrayBuffer, function (audioBuffer) {
                    resolve(audioBuffer);
                }, function (error) {
                    reject('Failed to decode audio data: ' + error);
                });
            });
        });
}

function playAudio(audioBuffer) {
    if (!audioContext) {
        audioContext = new (window.AudioContext || window.webkitAudioContext)();
    }

    const resumeAudioContext = () => {
        if (audioContext.state === 'suspended') {
            audioContext.resume().then(() => {
                console.log('AudioContext resumed successfully');
            });
        }
    };

    const playBuffer = () => {
        const audioSource = audioContext.createBufferSource();
        audioSource.buffer = audioBuffer;
        audioSource.connect(audioContext.destination);
        audioSource.start();
    };

    // Try to resume the AudioContext within a user-triggered event
    document.addEventListener('click', () => {
        resumeAudioContext();
        playBuffer();
    }, { once: true });
}