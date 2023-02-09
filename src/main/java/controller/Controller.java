package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.HttpRequest;

import java.io.DataOutputStream;
import java.io.IOException;

public abstract class Controller {

    static final Logger logger = LoggerFactory.getLogger(Controller.class);

    public void process(HttpRequest request, DataOutputStream dos) throws IOException {
        String method = request.getRequestHeader().get("method").orElseThrow(IllegalArgumentException::new);
        if (method.equals("GET")) {
            doGet(request, dos);
        }
        if (method.equals("POST")) {
            doPost(request, dos);
        }
        doFinally(request, dos);
    };

    protected void doGet(HttpRequest request, DataOutputStream dos) {}
    protected void doPost(HttpRequest request, DataOutputStream dos) {}
    protected void doFinally(HttpRequest request, DataOutputStream dos) {}

    void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

}
