# Example project how externalise content based routing decision.

TODO

## Running DMN with Kogito operator

Install RHPAM Kogito Operator to your OpenShift. Create a project to host your stuff.

See openshift dir for yamls.

Create a binary build for your app:
```
oc apply -f openshift/kogito-dmn-binary.yaml
```

Run ```mvn package``` in dmn-cbr dir.

Start the build.

```
oc start-build dmn-binary --from-dir=dmn-cbr/target --follow
```

When complete, deploy:
```
oc apply -f openshift/kogito-binary-runtime.yaml
```
