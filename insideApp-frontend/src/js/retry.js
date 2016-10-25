const retryTimeout = 2000;
const maxRetries = 1800; // 1800 tries * 2 seconds == 1 hour

function retry(promiseCreator) {
  let innerRetry = function (callCount) {
    promiseCreator()
      .catch(function (response) {
        if (callCount < maxRetries) {
          setTimeout(function () {
            innerRetry(callCount + 1);
          }, retryTimeout);
        }
        throw response;
      });
  };

  innerRetry(0);
}

export {
  retry
};